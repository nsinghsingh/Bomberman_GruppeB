package tiles;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class BasicTile extends JPanel{

    //TODO figure out how to save images and configure JPanels

    @Getter @Setter private boolean isSolid; //Can players go through
    @Getter @Setter private boolean isDestroyable; //Can a bomb destroy this tile
    @Getter private String imagePath; //file path for the image (only temporarily)

    public BasicTile clone() {
        BasicTile newTile = new BasicTile();
        setSolid(isSolid());
        setDestroyable(isDestroyable());
        setImagePath(getImagePath());
        newTile.setBackground(getBackground());
        return newTile;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image background = Toolkit.getDefaultToolkit().createImage(getImagePath());
        g.drawImage(background, 0, 0, null);
    }

}
