package tiles;
import forms.Labyrinth;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

public class Bomb extends BasicTile{

    @Getter @Setter private int timer;
    @Getter @Setter private int radius;
    @Getter @Setter private Labyrinth labyrinth;

    private Bomb(){
        setSolid(true);
        setDestroyable(true);
        setImage("");
        setTile(new JPanel());
    }

    public Bomb(Labyrinth labyrinth){
        this();
        setTimer(3);
        setRadius(2);
        setLabyrinth(labyrinth);
    }

    public Bomb(int timer, int radius, Labyrinth labyrinth){
        this();
        setTimer(timer);
        setRadius(radius);
        setLabyrinth(labyrinth);
    }

    public void explode(){
        countdown(timer);
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
