import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

public class Bomb extends Tile{

    @Getter @Setter private int timer;
    @Getter @Setter private int radius;
    @Getter @Setter private Labyrinth labyrinth;

    private Bomb(){
        super();
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

    @Override
    public void uponDestroyed() {
        //TODO figure out how to summon the explosion
    }
}
