package tiles;

import client.ClientMain;
import lombok.Getter;
import lombok.Setter;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Player extends BasicTile{

    @Getter @Setter private int xPosition;
    @Getter @Setter private int yPosition;
    @Getter @Setter private int targetX;
    @Getter @Setter private int targetY;
    @Getter @Setter private JLabel playerSprite;
    @Getter private String direction = "n";
    @Getter @Setter private JPanel field;
    @Getter @Setter private boolean isDead;
    @Getter private static int playerIndex;
    @Getter @Setter private ImageIcon sprite;

    public Player(JPanel field){
        setField(field);
        setSolid(true);
        setDestroyable(true);
        setImagePath("../sprites/tiles/Grass1.png");
        setPlayer();
    }

    public void setPlayer(){
        String initial = "";
        switch (playerIndex){
            case 1:
                initial = "black/B";
                playerIndex = 2;
                setXPosition(50);
                setYPosition(50);
                break;
            case 2:
                initial = "blue/Blue";
                playerIndex = 3;
                setXPosition(1000);
                setYPosition(50);
                break;
            case 3:
                initial = "red/R";
                playerIndex = 4;
                setXPosition(50);
                setYPosition(500);
                break;
            case 4:
                initial = "white/W";
                playerIndex = 1;
                setXPosition(1000);
                setYPosition(500);
                break;
            default:
                initial = "white/W";
                playerIndex = 1;
                break;
        }
        URL bUp = getClass().getResource("../sprites/players/" + initial + "Back.gif");
        URL bDown = getClass().getResource("../sprites/players/" + initial + "Front.gif");
        URL bRight = getClass().getResource("../sprites/players/" + initial + "Right.gif");
        URL bLeft = getClass().getResource("../sprites/players/" + initial + "Left.gif");
        URL bStand = getClass().getResource("../sprites/players/" + initial + "Stand.png");
        ImageIcon standSprite = new ImageIcon(bStand);
        ImageIcon upSprite = new ImageIcon(bUp);
        ImageIcon downSprite = new ImageIcon(bDown);
        ImageIcon rightSprite = new ImageIcon(bRight);
        ImageIcon leftSprite = new ImageIcon(bLeft);
        setPlayerSprite(new JLabel(standSprite, JLabel.CENTER));
        playerSprite.setMinimumSize(new Dimension(50, 50));
        add(playerSprite);
        setComponentZOrder(getFieldSprite(), 1);
        setComponentZOrder(playerSprite, 0);
    }

    @Override
    public BasicTile clone(){
        return new Player(field);
    }

    public void playerMove(){
        if(!getDirection().equals("n")) {
            int targetIndex = getTargetX() / getSize().width + getTargetY() / getSize().height * 22;
            int originIndex = getXPosition() / getSize().width + getYPosition() / getSize().height * 22;
            Component[] components = field.getComponents();
            field.removeAll();
            Component temp = components[targetIndex];
            components[targetIndex] = components[originIndex];
            components[originIndex] = temp;
            for (Component component : components) {
                field.add(component);
            }
            field.validate();
            setXPosition(getTargetX());
            setYPosition(getTargetY());
        }
    }

    public void setDirection(String direction){
        int width = getSize().width;
        int height = getSize().height;
        setTargetY(getYPosition());
        setTargetX(getXPosition());
        switch(direction){
            case "w":
                setTargetY(getYPosition() - height);
                break;
            case "a":
                setTargetX(getXPosition() - width);
                break;
            case "s":
                setTargetY(getYPosition() + height);
                break;
            case "d":
                setTargetX(getXPosition() + width);
                break;
            default:
                this.direction = "n";
        }
        try{
            int targetIndex = getTargetX() / getSize().width + getTargetY() / getSize().height * 22;
            BasicTile targetTile = (BasicTile) field.getComponent(targetIndex);
            if(!targetTile.isSolid()){
                this.direction = direction;
            }
            else{
                this.direction = "n";
            }
            playerMove();
        }
        catch (Exception ignored){
            System.out.println("error");
        }
    }

    public void placeBomb(){ }

    public void die(){

    }
}
