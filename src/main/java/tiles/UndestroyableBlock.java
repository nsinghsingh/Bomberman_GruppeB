package tiles;

import javax.swing.*;
import java.awt.*;

public class UndestroyableBlock extends BasicTile implements Tile{

    public UndestroyableBlock() {
        setSolid(true);
        setDestroyable(false);
        setImage("");
        JPanel background = new JPanel();
        background.setBackground(Color.GRAY);
        setTile(background);
    }
}
