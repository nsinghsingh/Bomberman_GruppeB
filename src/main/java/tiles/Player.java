package tiles;

import javax.swing.*;

public class Player extends BasicTile{

    public Player(){
        super();
        setSolid(true);
        setDestroyable(true);
        setImage("");
        setTile(new JPanel());
    }
}
