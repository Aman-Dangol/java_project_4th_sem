package main;

import entity.Bullet;
import entity.Player;
import tile.Background;
import tile.TileManager;
import Object.SuperObject;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    public int tileSize = 48;//for creating a tiles
    int column = 20;

    int rows = 12;

   public int screenWidth = column * tileSize;

    public int screenHeight = rows * tileSize;

   public final int maxWorldCol =50;
    public final int maxWorldRow=70;
    int FPS = 60;
    TileManager tileM;
    Background background=new Background(this);

    public MouseHandler mouseH = new MouseHandler();
    KeyHandler keyH=keyH=new KeyHandler();
    public Player player = new Player(this,keyH,mouseH);

    public Bullet bullet = new Bullet(player,this,mouseH);

    Sound Music = new Sound();
    Sound se = new Sound();

    public UI ui= new UI(this);

    public CollisionChecker collisionChecker = new CollisionChecker(this);

    AssetSetter aSetter= new AssetSetter(this);

    public SuperObject[] obj = new SuperObject[10];
    Thread thread;




    GamePanel() throws IOException {



        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        addKeyListener(keyH);
        addMouseListener(mouseH);
        addMouseMotionListener(mouseH);
        setFocusable(true);
        tileM=new TileManager(this,keyH);
    }

    public void setupGame(){
        aSetter.setObject();
        playMusic(4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long drawStart=0;
        if (keyH.checkDrawTime==true){
            drawStart = System.currentTimeMillis();
        }

        Graphics2D g2d = (Graphics2D) g;
        background.draw(g2d);
        tileM.draw(g2d);
        for (int i=0;i< obj.length;i++){
            if (obj[i]!=null){
                obj[i].draw(g2d,this);
            }
        }

        player.draw(g2d);
        if (mouseH.mousePressed){
            bullet.draw(g2d);
        }
        ui.draw(g2d);

        if (keyH.checkDrawTime==true){
            long drawEnd= System.currentTimeMillis();
            long passed=drawEnd-drawStart;
            g2d.drawString("draw time: "+passed,10,400);
            System.out.println(passed);
        }

        g2d.dispose();
    }
    @Override
    public void run() {
        double drawInterval = 1000 / FPS;//draw screen avery 0.01666 times==draw screen 60 times per second
        double nextDrawTime = System.currentTimeMillis() + drawInterval;//upper limit
        while (thread!=null) {//as long as gameThread obj exists it repeats the process,core component go game
            //update character position and drawing with updated information
            update();

            repaint();
            try {
                double remainingTime = nextDrawTime - System.currentTimeMillis();
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                nextDrawTime += drawInterval;
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void update()  {
        player.update();
        player.falling();

    }
    public void startingThread(){
        thread=new Thread(this);
        thread.start();

    }

    public void playMusic(int i){
        Music.setFile(i);
        Music.play();
        Music.loop();
    }
    public void stopMusic(){
        Music.stop();
    }
    public void soundSE(int i){
        se.setFile(i);
        se.play();
    }
}


