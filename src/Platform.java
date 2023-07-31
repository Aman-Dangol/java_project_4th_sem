import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Platform extends JFrame implements KeyListener, ActionListener {
    Player player = new Player();
    Timer timer = new Timer(45,this);

    Platform(){
        setTitle("game");//name shows in title bar
        setSize(new Dimension(500,500)); //setting the size of frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes the jframe after the x(title bar) button is pressed
        setLayout(null);
        add(player);
        timer.start();
        addKeyListener(this);
        player.setBounds(50,(getHeight()-137),100,100);//adding player component in JFrame
        setLocationRelativeTo(null);//centering the frame
        setVisible(true);


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar()==' '){
            player.jump();
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.falling();
    }
}

