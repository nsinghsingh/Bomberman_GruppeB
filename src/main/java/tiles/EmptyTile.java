package tiles;

import javax.swing.*;
import java.awt.*;

public class EmptyTile extends BasicTile implements Tile{

    public EmptyTile(){
        setSolid(false);
        setDestroyable(false);
        setImage("");
        JPanel background = new JPanel();
        background.setBackground(Color.GREEN);
        setTile(background);
    }

}
