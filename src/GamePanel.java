import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    int tileSize=48;//for creating a tiles
    int column=16;

    int rows=12;

    int width = column * tileSize;

    int height = rows * tileSize;

    int x =100;
    int y=height-tileSize;

    int speed =4;

    int FPS = 60;

    Thread thread;
    boolean up=false,down=false,left=false,right=false;

    Player player = new Player(this);


    GamePanel(){
        setPreferredSize(new Dimension(width,height));
        setBackground(Color.green);
       thread = new Thread(this);
        addKeyListener(this);
        setFocusable(true);
        thread.start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        player.draw(g2d);
  }

    void falling(){
        player.falling();
    }


    @Override
    public void run() {
        double drawInterval=1000/FPS;//draw screen avery 0.01666 times==draw screen 60 times per second
        double nextDrawTime = System.currentTimeMillis()+drawInterval;//upper limit
        while (true) {//as long as gameThread obj exists it repeats the process,core component go game
            //update character position and drawing with updated information
            update();
            falling();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.currentTimeMillis();
                if (remainingTime<0){
                    remainingTime=0;
                }
                nextDrawTime+=drawInterval;
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    void update(){
        if (up==true){
            player.update("up");
        }
       if (down==true){
           player.update("down");
        }
       if (left==true){
             player.update("left");
        }
       if (right==true){
             player.update("right");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode()==KeyEvent.VK_W){
            up=true;
        }
        if (e.getKeyCode()==KeyEvent.VK_S) {
           down=true;
        }
        if (e.getKeyCode()==KeyEvent.VK_D) {
           right=true;
       }
        if (e.getKeyCode()==KeyEvent.VK_A){
           left=true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_W){
                up=false;
            }
            else if (e.getKeyCode()==KeyEvent.VK_S) {
                down=false;
            }
            else if (e.getKeyCode()==KeyEvent.VK_D) {
                right=false;
            }
            else if (e.getKeyCode()==KeyEvent.VK_A){
                left=false;
            }
        }

}
