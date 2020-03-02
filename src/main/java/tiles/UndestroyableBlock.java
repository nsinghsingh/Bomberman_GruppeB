package tiles;

import javax.swing.*;
import java.awt.*;

public class UndestroyableBlock extends BasicTile{

    public UndestroyableBlock() {
        setSolid(true);
        setDestroyable(false);
        setImage("");
        setBackground(Color.GRAY);
    }
}
