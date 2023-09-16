package main;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static JFrame window;
    public static void main(String[] args) throws IOException {
         window =new JFrame();
        window.setTitle("game");//name shows in title bar
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes the JFrame after the x(title bar) button is pressed
        GamePanel gamePanel = new GamePanel();//instantiating an object of Game panel
        window.add(gamePanel);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);//centering the frame
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startingThread();
    }
}