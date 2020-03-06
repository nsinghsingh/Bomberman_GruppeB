package tiles;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BasicTile extends JPanel{

    //TODO figure out how to save images and configure JPanels

    @Getter @Setter private boolean isSolid;
    @Getter @Setter private boolean isDestroyable;
    @Getter private JLabel fieldSprite;
    @Getter private String imagePath;

    public BasicTile clone() {
        BasicTile newTile = new BasicTile();
        newTile.setLayout(new BorderLayout());
        newTile.setSolid(isSolid());
        newTile.setDestroyable(isDestroyable());
        newTile.setImagePath(getImagePath());
        newTile.setBackground(getBackground());
        return newTile;
    }

    public void setFieldSprite(JLabel label){
        this.fieldSprite = label;
        fieldSprite.setMinimumSize(new Dimension(50, 50));
        add(fieldSprite);
    }

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
        URL ground = getClass().getResource(imagePath);
        ImageIcon sprite = new ImageIcon(ground);
        setFieldSprite(new JLabel(sprite, JLabel.CENTER));
    }
}
