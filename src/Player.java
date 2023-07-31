import javax.swing.*;
import java.awt.*;

public class Player extends JLabel{
    int falling = 2;
    int  gravity = 1;

    Player(){
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(0,0,getWidth()-1,getHeight()-1);

    }
    void falling(){
        System.out.println(getY());
        setLocation(getX(),getY()+falling);
        falling+=gravity;
        if (getY()>=363){
                setLocation(getX(),363);
        }
    }
    void jump(){
        falling=-10;
    }
}
