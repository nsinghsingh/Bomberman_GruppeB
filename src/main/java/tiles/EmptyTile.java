package tiles;

public class EmptyTile extends BasicTile{

    public EmptyTile(){
        setSolid(false);
        setDestroyable(false);
        setFieldImagePath("../sprites/tiles/Grass1.png");
    }

    @Override
    public BasicTile getCopy() {
        return new EmptyTile();
    }

    @Override
    public void explode(int rotation, int range) {
        if(range == 1)
        setUpperImagePath("");
    }
}
