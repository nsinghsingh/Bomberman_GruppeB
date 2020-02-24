import javax.swing.*;

public class Block extends Tile{

    public Block(boolean isDestroyable) {
        super();
        if(isDestroyable){
            setSolid(true);
            setDestroyable(false);
            setImage("");
            setTile(new JPanel());
        }
        else{
            setSolid(true);
            setDestroyable(true);
            setImage("");
            setTile(new JPanel());
        }
    }

    @Override
    public void uponDestroyed() {
        setSolid(false);
        setDestroyable(false);
        setImage("");
        setTile(new JPanel());
    }
}
