package main;

import javax.swing.*;
import java.io.IOException;

public class Window extends JFrame {

    Window() throws IOException {
        setTitle("game");//name shows in title bar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes the JFrame after the x(title bar) button is pressed
        GamePanel gamePanel = new GamePanel();//instantiating an object of Game panel
        add(gamePanel);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);//centering the frame
        setVisible(true);
        gamePanel.startingThread();


    }


}


