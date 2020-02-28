package tiles;

import javax.swing.*;
import java.awt.*;

public class DestroyableBlock extends BasicTile implements Tile{

    public DestroyableBlock() {
        setSolid(true);
        setDestroyable(true);
        setImage("");
        JPanel background = new JPanel();
        background.setBackground(new Color(150, 75, 0));
        setTile(background);
    }
}
