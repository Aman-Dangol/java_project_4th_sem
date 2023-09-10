package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    GamePanel gp;
    Font arial_40 = new Font("Arial",Font.PLAIN,20);

    public boolean messageOn=false;
    public String message="";

    public int messageCounter=0;
    BufferedImage health,stamina;
    public  UI(GamePanel gp){
        this.gp=gp;
        try {
            health= ImageIO.read(getClass().getResourceAsStream("/player/health.png"));
            stamina=ImageIO.read(getClass().getResourceAsStream("/player/stamina.png"));

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void showMessage(String text){
        message=text;
        messageOn=true;

    }
    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.black);
        g2.drawImage(health,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
        g2.drawString(" : "+gp.player.health ,70,50);
        g2.drawImage(stamina,gp.tileSize/2,gp.tileSize/2+50,gp.tileSize,gp.tileSize,null);

        g2.drawString(" : "+gp.player.stamina ,70,90);

       if (messageOn){
           g2.setFont(g2.getFont().deriveFont(18F));
           g2.drawString(message,gp.tileSize*8,gp.tileSize*3);
           messageCounter++;
           if (messageCounter>120){
               messageCounter=0;
               messageOn=false;
           }
       }
    }

}
