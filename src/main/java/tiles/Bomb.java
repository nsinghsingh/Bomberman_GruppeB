package tiles;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

/** A bomb tile which explodes and destroy any detroyable or player tiles**/

public class Bomb extends BasicTile{

    @Getter @Setter private int timer;
    @Getter @Setter private int radius;
    @Getter @Setter private JPanel labyrinth;
    @Getter @Setter private Player player;
    @Getter @Setter private int xPosition;
    @Getter @Setter private int yPosition;
    private Timer counter;

    private Bomb(){
        setLayout(new BorderLayout());
        setSolid(true);
        setDestroyable(true);
        setFieldImagePath("../sprites/tiles/Grass1.png");
        setUpperImagePath("../sprites/bomb/Bomb.png");
    }

    public Bomb(JPanel labyrinth, Player player){
        this(2,2, labyrinth, player);
    }

    //Starts a timer for exploding as soon as it is initialized

    public Bomb(int timer, int radius, JPanel labyrinth, Player player){
        this();
        setTimer(timer);
        setRadius(radius);
        setLabyrinth(labyrinth);
        setPlayer(player);
        setXPosition(player.getXPosition() / player.getSize().width);
        setYPosition(player.getYPosition() / player.getSize().height);
        countdown();
    }

    //Override from parent so it returns a new object of type bomb instead of a basictile

    @Override
    public BasicTile getCopy() {
        return new Bomb(timer, radius, labyrinth, player);
    }

    //Timer for exploding

    public void countdown(){
        counter = new Timer(0, evt -> explodeAround());
        counter.setInitialDelay(timer * 1000);
        counter.setRepeats(false);
        counter.start();
    }

    //Explodes all destroyable or non-solid tiles in the horizontal and vertical range of the bomb

    public void explodeAround(){
        for (int i = 0; i < radius * 2 + 1; i++) {
            int position = xPosition - radius + i + yPosition * 22;
            if((xPosition - radius + i) > 0 && (xPosition - radius + i) < 22){
                BasicTile tile = (BasicTile) labyrinth.getComponent(position);
                if(tile.isDestroyable() || !tile.isSolid()){
                    if(i < 2){
                        tile.explode(270, i);
                    }
                    else if(i > 2){
                        tile.explode(90, i % 2);
                    }
                }
            }
        }
        for (int i = 0; i < radius * 2 + 1; i++) {
            int position = xPosition + (yPosition - radius + i) * 22;
            if((yPosition - radius + i) > 0 && (yPosition - radius + i) < 12){
                BasicTile tile = (BasicTile) labyrinth.getComponent(position);
                if(tile.isDestroyable() || !tile.isSolid()){
                    if(i < 2){
                        tile.explode(0, i);
                    }
                    else if(i > 2){
                        tile.explode(180, i % 2);
                    }
                }
            }
        }
        explodeCenter();
    }

    //Explodes itself and after some time it replaces the tile on which it is with an empty tile

    public void explodeCenter(){
        player.bombsPlaced -= 1;
        setUpperImagePath("../sprites/bomb/ExpMid.png");
        counter = new Timer(0, e -> {
            int position = xPosition + yPosition * 22;
            Component[] components = labyrinth.getComponents();
            labyrinth.removeAll();
            components[position] = new EmptyTile();
            for (Component component : components) {
                labyrinth.add(component);
            }
            labyrinth.validate();
        });
        counter.setInitialDelay(1000);
        counter.setRepeats(false);
        counter.start();
    }

    //Stops the timer and makes it blow up instantly

    @Override
    public void explode(int rotation, int range) {
        counter.stop();
        explodeAround();
    }
}
