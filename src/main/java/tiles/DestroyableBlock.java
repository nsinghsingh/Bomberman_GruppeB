package tiles;

public class DestroyableBlock extends BasicTile{

    public DestroyableBlock() {
        setSolid(true);
        setDestroyable(true);
        setFieldImagePath("../sprites/tiles/BrittleWall.png");
    }

    @Override
    public BasicTile getCopy() {
        return new DestroyableBlock();
    }

    @Override
    public void explode(int rotation, int range) {
        super.explode(rotation, range);
        setSolid(false);
        setDestroyable(false);
        setFieldImagePath("../sprites/tiles/BurnedStoneGrass.png");
    }
}
