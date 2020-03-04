package tiles;

import java.awt.*;

public class DestroyableBlock extends BasicTile{

    public DestroyableBlock() {
        setSolid(true);
        setDestroyable(true);
        setImagePath("");
        setBackground(new Color(150, 75, 0));
    }
}
