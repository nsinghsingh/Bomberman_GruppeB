package tiles;

public class UndestroyableBlock extends BasicTile{

    public UndestroyableBlock() {
        setSolid(true);
        setDestroyable(false);
        setFieldImagePath("../sprites/tiles/Wall.png");
    }

    @Override
    public BasicTile getCopy() {
        return new UndestroyableBlock();
    }
}
