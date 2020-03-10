package tiles;
import forms.Labyrinth;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

public class Bomb extends BasicTile{

    @Getter @Setter private int timer;
    @Getter @Setter private int radius;
//    @Getter @Setter private Player player;
    @Getter @Setter private int bombX;
    @Getter @Setter private int bombY;
    @Getter @Setter private boolean isExploded = true;

    Bomb(){
            setSolid(true);
            setDestroyable(true);
            setImagePath("../sprites/bomb/Bomb.gif");
        }

    public Bomb(int bombX, int bombY){
            this();
            setTimer(3);
            setRadius(2);
            setBombX(bombX);
            setBombY(bombY);
//            setPlayer(player);
        }

    public Bomb(int timer, int radius, int bombX, int bombY){
            this();
            setTimer(timer);
            setRadius(radius);
    }

    public void explode(){
        countdown(timer);
        isExploded = true;
    }

    public void countdown(int seconds){
        long startTime = System.currentTimeMillis() / 1000;
        long elapsedTime = System.currentTimeMillis() / 1000 - startTime;
        while(elapsedTime < seconds){
            elapsedTime = System.currentTimeMillis() / 1000 - startTime;
        }
    }

    //TODO figure out how to summon the explosion
}
