package tiles;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Player extends BasicTile{

    @Getter @Setter private int xPosition = 50;
    @Getter @Setter private int yPosition = 50;
    @Getter @Setter private int targetX = 50;
    @Getter @Setter private int targetY = 50;
    @Getter @Setter private BasicTile[][] tiles;
    @Getter @Setter private JLabel playerIcon;
    @Getter @Setter private String direction = "n";
    @Getter @Setter private BasicTile currentTile;

    public Player(BasicTile[][] tiles){
        setSolid(true);
        setDestroyable(true);
        setImagePath("");
        setTiles(tiles);
        currentTile = this;
        URL imgURL = getClass().getResource("../icons/BIcon.png");
        ImageIcon icon = new ImageIcon(imgURL);
        setPlayerIcon(new JLabel(icon, JLabel.CENTER));
        playerIcon.setMinimumSize(new Dimension(50, 50));
        add(playerIcon);
    }

    @Override
    public BasicTile clone(){
        return new Player(getTiles());
    }

    public void playerMove(){ //TODO Change movement mid-timer and make it visible
        Timer timer = new Timer(20, null);
        timer.addActionListener(evt -> {
            if(!getDirection().equals("n")){
                setDirection(getDirection());
                int xMovement = (getTargetX() - getXPosition()) / getSize().width;
                int yMovement = (getTargetY() - getYPosition()) / getSize().height;
                playerIcon.setLocation(playerIcon.getX() + xMovement, playerIcon.getY() + yMovement);
                setXPosition(getXPosition() + xMovement);
                setYPosition(getYPosition() + yMovement);
                BasicTile target = getTiles()[getXPosition()/getSize().width][getYPosition()/getSize().height];
                if(getCurrentTile() != target){
                    target.setSolid(true);
                    currentTile.setSolid(false);
                    setCurrentTile(target);
                }
            }
        });
        timer.start();
    }

    public void setDirection(String direction){
        int width = getSize().width;
        int height = getSize().height;
        int originY = getYPosition();
        int originX = getXPosition();
        setTargetY(originY);
        setTargetX(originX);
        switch(direction){
            case "w":
                setTargetY(originY - height);
                break;
            case "a":
                setTargetX(originX - width);
                break;
            case "s":
                setTargetY(originY + height);
                break;
            case "d":
                setTargetX(originX + width);
                break;
            default:
                this.direction = "n";
        }
        try{
            BasicTile targetTile = getTiles()[getTargetX()/width][getTargetY()/height];
            if(!targetTile.isSolid()){
                this.direction = direction;
            }
            else{
                this.direction = "n";
            }
        }
        catch (Exception ignored){}
    }

    public void placeBomb(){

    }

    public void die(){

    }
}
