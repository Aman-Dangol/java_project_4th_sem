import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Test  extends JFrame implements KeyListener, ActionListener {
    Player player = new Player();
    Timer timer = new Timer(10,this);

    Test(){
        setTitle("test");
        setSize(new Dimension(500,500));
        getContentPane().setPreferredSize(new Dimension(500,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        add(player);
        player.setBounds(50,(getHeight()-137),100,100);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        player.falling();
    }

}
