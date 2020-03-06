package tiles;

import java.awt.*;

public class EmptyTile extends BasicTile{

    public EmptyTile(){
        setSolid(false);
        setDestroyable(false);
        setImagePath("../sprites/tiles/Grass1.png");
    }

}
