package tiles;

import tiles.EmptyTile;

import javax.swing.*;

public class Player extends BasicTile implements Tile{

    public Player(){
        super();
        setSolid(true);
        setDestroyable(true);
        setImage("");
        setTile(new JPanel());
    }
}
