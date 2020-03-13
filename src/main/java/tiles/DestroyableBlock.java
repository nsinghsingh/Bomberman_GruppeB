package tiles;

/** A tile which can be destroyed and can't be walked through**/

public class DestroyableBlock extends BasicTile{

    public DestroyableBlock() {
        setSolid(true);
        setDestroyable(true);
        setFieldImagePath("../sprites/tiles/BrittleWall.png");
    }

    //Returns a tile of type destroyableblock

    @Override
    public BasicTile getCopy() {
        return new DestroyableBlock();
    }

    //After exploding it is no longer solid or destroyable

    @Override
    public void explode(int rotation, int range) {
        super.explode(rotation, range);
        setSolid(false);
        setDestroyable(false);
    }
}
