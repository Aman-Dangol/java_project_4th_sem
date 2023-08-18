package tile;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {


    GamePanel gp;
    KeyHandler keyH;
    public  int backgroundX = 0;
    public int backgroundY = 0;
    public int backgroundImageWidth;
    public  int backgroundImageHeight;
    public Image background;

    public TileManager(GamePanel gp,KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;
        getTileImage();
        // Get the background image dimensions
        backgroundImageWidth =background.getWidth(null);
        backgroundImageHeight =background.getHeight(null);
    }
    public void getTileImage(){
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/background/background.png"));
        background = imageIcon.getImage();

    }

    public void draw(Graphics2D g2){


        int offsetX=gp.player.screenX-gp.player.x;
        int offsetY=gp.player.screenY-gp.player.y;
        g2.drawImage(background,backgroundX+offsetX,backgroundY+offsetY,null);
    }






}

