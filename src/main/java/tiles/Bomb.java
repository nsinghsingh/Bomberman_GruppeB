package tiles;
import forms.Labyrinth;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

public class Bomb extends BasicTile{

    @Getter @Setter private int timer;
    @Getter @Setter private int radius;
    @Getter @Setter private JPanel labyrinth;
    @Getter @Setter private Player player;

    private Bomb(){
        setSolid(true);
        setDestroyable(true);
        setFieldImagePath("../sprites/tiles/Grass1.png");
        setUpperImagePath("../sprites/bomb/Bomb.gif");
    }

    public Bomb(JPanel labyrinth, Player player){
        this();
        setTimer(3);
        setRadius(2);
        setLabyrinth(labyrinth);
        setPlayer(player);
    }

    public Bomb(int timer, int radius, JPanel labyrinth, Player player){
        this();
        setTimer(timer);
        setRadius(radius);
        setLabyrinth(labyrinth);
        setPlayer(player);
    }

    @Override
    public BasicTile getCopy() {
        return new Bomb(timer, radius, labyrinth, player);
    }

    public void countdown(int seconds){
        long startTime = System.currentTimeMillis() / 1000;
        long elapsedTime = System.currentTimeMillis() / 1000 - startTime;
        while(elapsedTime < seconds){
            elapsedTime = System.currentTimeMillis() / 1000 - startTime;
        }
    }

    @Override
    public void explode(int rotation, int range) {
        //TODO summon explosion
        player.bombsPlaced -= 1;
    }
}
