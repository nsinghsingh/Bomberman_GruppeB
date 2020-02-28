package tiles;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

public class BasicTile {

    //TODO figure out how to save images and configure JPanels

    @Getter @Setter private boolean isSolid; //Can players go through
    @Getter @Setter private boolean isDestroyable; //Can a bomb destroy this tile
    @Getter @Setter private String image; //file path for the image (only temporarily)
    @Getter @Setter private JPanel tile;

    public BasicTile clone() {
        BasicTile tile = new BasicTile();
        setSolid(tile.isSolid());
        setDestroyable(tile.isDestroyable());
        setImage(tile.getImage());
        JPanel background = new JPanel();
        background.setBackground(tile.getTile().getBackground());
        setTile(background);
        return tile;
    }

}
