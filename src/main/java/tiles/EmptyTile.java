package tiles;

import java.awt.*;

public class EmptyTile extends BasicTile{

    public EmptyTile(){
        setSolid(false);
        setDestroyable(false);
        setImage("");
        setBackground(Color.GREEN);
    }

}
