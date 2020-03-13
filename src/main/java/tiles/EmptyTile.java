package tiles;

import java.awt.*;

/** A tile which can't be destroyed but can be walked through**/

public class EmptyTile extends BasicTile{

    public EmptyTile(){
        setLayout(new BorderLayout());
        setSolid(false);
        setDestroyable(false);
        setFieldImagePath("../sprites/tiles/Grass1.png");
    }

    //Returns a tile of type empty tile

    @Override
    public BasicTile getCopy() {
        return new EmptyTile();
    }
}
