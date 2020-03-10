package tiles;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BasicTile extends JPanel{

    @Getter @Setter private boolean isSolid;
    @Getter @Setter private boolean isDestroyable;
    @Getter private JLabel fieldSprite;
    @Getter private JLabel upperSprite;
    @Getter private String fieldImagePath;
    @Getter private String upperImagePath;

    public BasicTile clone() {
        BasicTile newTile = getCopy();
        newTile.setLayout(new BorderLayout());
        newTile.setSolid(isSolid());
        newTile.setDestroyable(isDestroyable());
        newTile.setFieldImagePath(getFieldImagePath());
        newTile.setBackground(getBackground());
        if(getUpperSprite() != null){
            newTile.setUpperSprite(getUpperSprite());
        }
        return newTile;
    }

    public BasicTile getCopy(){
        return new BasicTile();
    }

    public void setFieldSprite(JLabel label){
        this.fieldSprite = label;
        fieldSprite.setMinimumSize(new Dimension(50, 50));
        fieldSprite.setPreferredSize(new Dimension(50, 50));
        add(fieldSprite);
    }

    public void setFieldImagePath(String fieldImagePath){
        this.fieldImagePath = fieldImagePath;
        URL ground = getClass().getResource(fieldImagePath);
        ImageIcon sprite = new ImageIcon(ground);
        setFieldSprite(new JLabel(sprite, JLabel.CENTER));
    }

    public void setUpperSprite(JLabel label){
        this.upperSprite = label;
        upperSprite.setMinimumSize(new Dimension(50, 50));
        upperSprite.setPreferredSize(new Dimension(50, 50));
        fieldSprite.setLayout(new BorderLayout());
        fieldSprite.add(upperSprite);
    }

    public void setUpperImagePath(String upperImagePath){
        this.upperImagePath = upperImagePath;
        URL ground = getClass().getResource(upperImagePath);
        ImageIcon sprite = new ImageIcon(ground);
        setUpperSprite(new JLabel(sprite, JLabel.CENTER));
    }

    public void explode(int rotation, int range) {
        String section = "";
        if(range == 1){
            section = "Mid";
        }
        else{
            section = "End";
        }
        String degree = "";
        switch(rotation){
            case 0:
                degree = "Up";
                break;
            case 90:
                degree = "Right";
                break;
            case 180:
                degree = "Down";
                break;
            case 270:
                degree = "Left";
                break;
        }
        setUpperImagePath("../sprites/bomb/Exp" + section + degree + ".gif");
    }
}
