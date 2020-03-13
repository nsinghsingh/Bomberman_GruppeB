package tiles;

import forms.Labyrinth;
import lombok.Getter;
import lombok.Setter;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

/** A tile which one of the clients can move with inputs. It can also place bombs and be destroyed **/

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
    @Getter @Setter private int playerIndex;
    private Component temp;
    public int bombsPlaced;

    //Gets a reference to the field and the labyrinth class which is connected to a client

    public Player(Labyrinth labyrinth){
        setLayout(new BorderLayout());
        setField(labyrinth.getGameRender());
        setLabyrinth(labyrinth);
        setSolid(true);
        setDestroyable(true);
        setFieldImagePath("../sprites/tiles/Grass1.png");
    }

    //Depending on playerIndex it will load a different image for the player

    public void setPlayer(int playerIndex){
        setPlayerIndex(playerIndex);
        String initial;
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

    //Returns an object of type player

    @Override
    public BasicTile getCopy(){
        return new Player(labyrinth);
    }

    /*
    Switches the player with the tile it is heading to, updates the position and loads the map again. If the variable
    temp has a bomb in it, then it switches with the bomb instead of the tile thus placing the bomb
     */

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
            components[targetIndex] = this;
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

    //Gets the input from client through labyrinth and calculates wether it is possible to go there.

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
            System.out.println("invalid position");
        }
    }

    /*
    If the player hasn't placed the maximum amount of bombs it can then it sets temp to bomb and temporarily adds a
    bomb on top of the player until he moves
     */

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

    //If the player who died is the same as the client then send a death message to the server

    @Override
    public void explode(int rotation, int range) {
        super.explode(rotation, range);
        if(labyrinth.getCLIENT_ID() == playerIndex){
            labyrinth.getCLIENT().sendMessage("method;k;" + labyrinth.getCLIENT_ID());
        }
    }

    //Replaces the tile it was on with an empty tile and sets himself as dead

    public void die(){
        if (!isDead) {
            setDead(true);
            Component[] components = field.getComponents();
            field.removeAll();
            int originIndex = xPosition / getSize().width + yPosition / getSize().height * 22;
            components[originIndex] = new EmptyTile();
            for (Component component : components) {
                field.add(component);
            }
            field.validate();
        }
    }
}
