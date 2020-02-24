import javax.swing.*;
import java.awt.*;

public class Block extends Tile{

    public Block(boolean isDestroyable) {
        super();
        if(isDestroyable){
            setSolid(true);
            setDestroyable(true);
            setImage("");
            JPanel background = new JPanel();
            background.setBackground(new Color(150,75,0));
            setTile(background);
        }
        else{
            setSolid(true);
            setDestroyable(false);
            setImage("");
            JPanel background = new JPanel();
            background.setBackground(Color.GRAY);
            setTile(background);
        }
    }
}
