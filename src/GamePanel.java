import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    int tileSize=48;//for creating a tiles
    int column=16;

    int rows=12;

    int width = column * tileSize;

    int height = rows * tileSize;

    int x =100;
    int y=100;

    int speed =4;

    int FPS = 60;

    Thread thread;
    boolean up=false,down=false,left=false,right=false;


    GamePanel(){

        setPreferredSize(new Dimension(width,height));
        setBackground(Color.green);
        setDoubleBuffered(true);
        thread = new Thread(this);
        addKeyListener(this);
        setFocusable(true);
        thread.start();

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillRect(x,y,tileSize,tileSize);
        g2d.dispose();
    }

    void falling(){
        System.out.println(y);
        if(y<height-tileSize){
            y += 1;
        }
    }


    @Override
    public void run() {
        double drawInterval=1000/FPS;//draw screen avery 0.01666 times==draw screen 60 times per second
        double nextDrawTime = System.currentTimeMillis()+drawInterval;
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
            y-=speed;
        }
       if (down==true){
                y += speed;

        }
         if (left==true){
            x-=speed;
        }
         if (right==true){
            x+=speed;
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
                speed=4;
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
