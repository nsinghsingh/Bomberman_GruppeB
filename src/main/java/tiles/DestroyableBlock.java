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
}
