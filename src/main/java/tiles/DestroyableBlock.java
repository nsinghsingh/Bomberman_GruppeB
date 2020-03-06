package tiles;

import java.awt.*;

public class DestroyableBlock extends BasicTile{

    public DestroyableBlock() {
        setSolid(true);
        setDestroyable(true);
        setImagePath("../sprites/tiles/BrittleWall.png");
    }
}
