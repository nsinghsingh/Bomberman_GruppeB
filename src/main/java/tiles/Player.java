package tiles;

import forms.Labyrinth;
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
    @Getter private String direction = "n";
    @Getter @Setter private JPanel field;
    @Getter @Setter private Labyrinth labyrinth;
    @Getter @Setter private boolean isDead;
    @Getter @Setter private ImageIcon sprite;
    private Component temp;
    public int bombsPlaced;

    public Player(Labyrinth labyrinth){
        setLayout(new BorderLayout());
        setField(labyrinth.getGameRender());
        setLabyrinth(labyrinth);
        setSolid(true);
        setDestroyable(true);
        setFieldImagePath("../sprites/tiles/Grass1.png");
    }

    public void setPlayer(int playerIndex){
        String initial = "";
        switch (playerIndex){
            case 0:
                initial = "black/B";
                setXPosition(50);
                setYPosition(50);
                break;
            case 1:
                initial = "red/R";
                setXPosition(1000);
                setYPosition(50);
                break;
            case 2:
                initial = "blue/Blue";
                setXPosition(50);
                setYPosition(500);
                break;
            default:
                initial = "white/W";
                setXPosition(1000);
                setYPosition(500);
                break;
        }
        URL bStand = getClass().getResource("../sprites/players/" + initial + "Stand.png");
        ImageIcon standSprite = new ImageIcon(bStand);
        setUpperSprite(new JLabel(standSprite, JLabel.CENTER));
    }

    @Override
    public BasicTile getCopy(){
        return new Player(labyrinth);
    }

    public void playerMove(){
        if(!getDirection().equals("n")) {
            getUpperSprite().removeAll();
            int targetIndex = targetX / getSize().width + targetY / getSize().height * 22;
            int originIndex = xPosition / getSize().width + yPosition / getSize().height * 22;
            Component[] components = field.getComponents();
            field.removeAll();
            if(temp == null){
                temp = components[targetIndex];
            }
            components[targetIndex] = components[originIndex];
            components[originIndex] = temp;
            for (Component component : components) {
                field.add(component);
            }
            field.validate();
            setXPosition(targetX);
            setYPosition(targetY);
            temp = null;
        }
    }

    public void setDirection(String direction){
        int width = getSize().width;
        int height = getSize().height;
        setTargetY(yPosition);
        setTargetX(xPosition);
        switch(direction){
            case "w":
                setTargetY(yPosition - height);
                break;
            case "a":
                setTargetX(xPosition - width);
                break;
            case "s":
                setTargetY(yPosition + height);
                break;
            case "d":
                setTargetX(xPosition + width);
                break;
            default:
                this.direction = "n";
        }
        try{
            int targetIndex = targetX / getSize().width + targetY / getSize().height * 22;
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

    public void placeBomb(){
        if (bombsPlaced < 1){
            temp = new Bomb(field, this);
            getUpperSprite().setLayout(new BorderLayout());
            URL ground = getClass().getResource("../sprites/bomb/Bomb.png");
            ImageIcon sprite = new ImageIcon(ground);
            getUpperSprite().add(new JLabel(sprite, JLabel.CENTER));
            bombsPlaced++;
        }
    }

    @Override
    public void explode(int rotation, int range) {
        super.explode(rotation, range);
        //die(); TODO fix death
    }

    public void die(){
        if (!isDead) {
            Component[] components = field.getComponents();
            field.removeAll();
            int originIndex = xPosition / getSize().width + yPosition / getSize().height * 22;
            components[originIndex] = new EmptyTile();
            for (Component component : components) {
                field.add(component);
            }
            field.validate();
            labyrinth.getCLIENT().sendMessage("method;k;" + labyrinth.getCLIENT_ID());
        }
    }
}
