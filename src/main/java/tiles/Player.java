package tiles;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

public class Player extends BasicTile{

    @Getter @Setter private int xPosition;
    @Getter @Setter private int yPosition;
    @Getter @Setter private BasicTile[][] tiles;
    @Getter @Setter private JLabel playerIcon;

    public Player(BasicTile[][] tiles){
        setSolid(true);
        setDestroyable(true);
        setImagePath("");
        setTiles(tiles);
        playerIcon = new JLabel();
        ImageIcon icon = new ImageIcon("icons/black-player.png");
        playerIcon.setIcon(icon);
    }

    @Override
    public BasicTile clone(){
        return new Player(getTiles());
    }

    public void playerMove(int x, int y, BasicTile target, BasicTile current){
        int xMovement = x - getXPosition();
        int yMovement = y - getYPosition();
        Timer timer = new Timer(50, null);
        timer.addActionListener(evt -> {
            setXPosition(getXPosition() + xMovement / 20);
            setYPosition(getYPosition() + yMovement / 20);
            playerIcon.setLocation(getXPosition(), getYPosition());
            if(getXPosition() % getWidth() > xMovement/2 || getYPosition() % getHeight() > yMovement/2){
                target.setSolid(true);
                current.setSolid(false);
            }
            if(getXPosition() == x && getYPosition() == y){
                timer.stop();
            }
        });
        timer.start();
    }

    public void setDirection(String direction){
        int width = getSize().width;
        int height = getSize().height;
        BasicTile currentTile = getTiles()[getXPosition()/width][getYPosition()/height];
        int targetY = getYPosition();
        int targetX = getXPosition();
        switch(direction){
            case "w":
                targetY = getYPosition() + height;
                break;
            case "a":
                targetX = getXPosition() - width;
                break;
            case "s":
                targetY = getYPosition() - height;
                break;
            case "d":
                targetX = getXPosition() + width;
                break;
        }
        try{
            BasicTile targetTile = getTiles()[targetX/width][targetY/height];
            if(!targetTile.isSolid()){
                playerMove(targetX, targetY, targetTile, currentTile);
            }
        }
        catch (Exception ignored){}
    }

    public void placeBomb(){}

    public void die(){}
}
