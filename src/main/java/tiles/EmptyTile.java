package tiles;

import java.awt.*;

public class EmptyTile extends BasicTile{

    public EmptyTile(){
        setLayout(new BorderLayout());
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
        super.explode(rotation, range);
    }
}
