package tiles;

/** A tile which can't be destroyed and can't be walked through**/

public class UndestroyableBlock extends BasicTile{

    public UndestroyableBlock() {
        setSolid(true);
        setDestroyable(false);
        setFieldImagePath("../sprites/tiles/Wall.png");
    }

    //Returns a tile of type undestroyableblock

    @Override
    public BasicTile getCopy() {
        return new UndestroyableBlock();
    }
}
