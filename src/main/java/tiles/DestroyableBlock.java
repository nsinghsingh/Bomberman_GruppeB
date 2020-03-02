package tiles;

import java.awt.*;

public class DestroyableBlock extends BasicTile{

    public DestroyableBlock() {
        setSolid(true);
        setDestroyable(true);
        setImage("");
        setBackground(new Color(150, 75, 0));
    }
}
