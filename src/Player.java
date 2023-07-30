import javax.swing.*;
import java.awt.*;

public class Player extends JPanel {
    int falling = 2;
    int  gravity = 1;

    Player(){
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println(getHeight());
        g.fillOval(0,0,getWidth(),getHeight());

    }
    void falling(){

        setLocation(getX(),getY()+falling);
        falling+=gravity;
    }
}
