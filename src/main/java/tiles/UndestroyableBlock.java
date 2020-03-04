package tiles;

import java.awt.*;

public class UndestroyableBlock extends BasicTile{

    public UndestroyableBlock() {
        setSolid(true);
        setDestroyable(false);
        setImagePath("");
        setBackground(Color.GRAY);
    }
}
