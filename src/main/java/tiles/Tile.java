package tiles;

import javax.swing.*;

public interface Tile {

    boolean isSolid = false;
    boolean isDestroyable = false;
    String image = "";
    JPanel tile = null;

    void clone(Tile tile);

    boolean isSolid();

    boolean isDestroyable();

    String getImage();

    JPanel getTile();

    void setSolid(boolean isSolid);

    void setDestroyable(boolean isDestroyable);

    void setImage(String image);

    void setTile(JPanel tile);
}
