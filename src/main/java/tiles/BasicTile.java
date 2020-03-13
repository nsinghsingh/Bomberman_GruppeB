package tiles;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**Base class for all tiles in the field. It has the basics methods which every class uses**/

public class BasicTile extends JPanel{

    @Getter @Setter private boolean isSolid;
    @Getter @Setter private boolean isDestroyable;
    @Getter private JLabel fieldSprite;
    @Getter private JLabel upperSprite;
    @Getter private String fieldImagePath;
    @Getter private String upperImagePath;

    //Returns an identical copy of this object

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

    //Returns an object of the same type of this class

    public BasicTile getCopy(){
        return new BasicTile();
    }

    //Removes all objects on this tile and adds a new fieldSprite JLabel

    public void setFieldSprite(JLabel label){
        this.fieldSprite = label;
        fieldSprite.setMinimumSize(new Dimension(50, 50));
        removeAll();
        add(fieldSprite);
    }

    //Makes a new JLabel from the string and sets it as fieldSprite

    public void setFieldImagePath(String fieldImagePath){
        this.fieldImagePath = fieldImagePath;
        URL ground = getClass().getResource(fieldImagePath);
        ImageIcon sprite = new ImageIcon(ground);
        setFieldSprite(new JLabel(sprite, JLabel.CENTER));
    }

    //Removes all objects on fieldSprite JLabel and adds a new upperSprite JLabel

    public void setUpperSprite(JLabel label){
        this.upperSprite = label;
        upperSprite.setMinimumSize(new Dimension(50, 50));
        fieldSprite.setLayout(new BorderLayout());
        fieldSprite.removeAll();
        fieldSprite.add(upperSprite);
    }

    //Makes a new JLabel from the string and sets it as upperSprite

    public void setUpperImagePath(String upperImagePath){
        this.upperImagePath = upperImagePath;
        URL ground = getClass().getResource(upperImagePath);
        ImageIcon sprite = new ImageIcon(ground);
        setUpperSprite(new JLabel(sprite, JLabel.CENTER));
    }

    //Loads an explosion for this tile depending on the range and rotation and after some time returns to normal

    public void explode(int rotation, int range) {
        String section = "";
        if(range == 1){
            section = "Side";
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
        setUpperImagePath("../sprites/bomb/Exp" + section + degree + ".png");
        Timer timer = new Timer(0, e -> {
            setFieldImagePath("../sprites/tiles/Grass1.png");
        });
        timer.setInitialDelay(1000);
        timer.setRepeats(false);
        timer.start();
    }
}
