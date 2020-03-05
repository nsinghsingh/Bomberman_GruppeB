package tiles;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class BasicTile extends JPanel{

    //TODO figure out how to save images and configure JPanels

    @Getter @Setter private boolean isSolid; //Can players go through
    @Getter @Setter private boolean isDestroyable; //Can a bomb destroy this tile
    @Getter @Setter private String imagePath; //file path for the image (only temporarily)

    public BasicTile clone() {
        BasicTile newTile = new BasicTile();
        newTile.setSolid(isSolid());
        newTile.setDestroyable(isDestroyable());
        newTile.setImagePath(getImagePath());
        newTile.setBackground(getBackground());
        return newTile;
    }
}
