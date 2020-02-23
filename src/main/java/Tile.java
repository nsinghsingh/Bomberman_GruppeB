import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

public class Tile {

    //TODO figure out how to save images and configure JPanels

    @Getter @Setter private boolean isSolid; //Can players go through
    @Getter @Setter private boolean isDestroyable; //Can a bomb destroy this tile
    @Getter @Setter private String image; //file path for the image (only temporarily)
    @Getter @Setter private JPanel tile;

    public Tile(){
        setSolid(false);
        setDestroyable(false);
        setImage("");
        setTile(new JPanel());
    }

    public void uponDestroyed(){}
}
