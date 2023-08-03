import javax.swing.*;

public class Window extends JFrame {

    Window(){
        setTitle("game");//name shows in title bar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes the JFrame after the x(title bar) button is pressed
        GamePanel gamePanel = new GamePanel();//instantiating an object of Game panel
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);//centering the frame
        setVisible(true);


    }


}

