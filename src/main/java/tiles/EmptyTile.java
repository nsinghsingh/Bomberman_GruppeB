package tiles;

import java.awt.*;

public class EmptyTile extends BasicTile{

    public EmptyTile(){
        setSolid(false);
        setDestroyable(false);
        setImagePath("");
        setBackground(Color.green);
    }

}
