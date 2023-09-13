package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial_40 , arial_80B;

    public boolean messageOn=false;
    public String message="";

    public int messageCounter=0;

    public Graphics2D g2;
    BufferedImage health,stamina;

    public boolean gameFinished=false;
    double playTime;

    UtilityTool utilityTool = new UtilityTool();
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    public  UI(GamePanel gp){
        this.gp=gp;
        arial_40 = new Font("Arial",Font.PLAIN,20);
        arial_80B = new Font("Arial",Font.BOLD,80);

//        try {
//            health= ImageIO.read(getClass().getResourceAsStream("/player/health.png"));
//            stamina=ImageIO.read(getClass().getResourceAsStream("/player/stamina.png"));
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        health=setup("health");
        stamina=setup("stamina");

    }
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image=null;
        try {
            image=ImageIO.read(getClass().getResourceAsStream("/player/"+imageName+".png"));
            image=uTool.scaledImage(image, gp.tileSize,gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void showMessage(String text){
        message=text;
        messageOn=true;

    }
    public void draw(Graphics2D g2){
//        if (gameFinished){
//            g2.setFont(arial_40);
//            g2.setColor(Color.white);
//
//
//            String text;
//            int textLength;
//            int x,y;
//
//            text="You Won";
//            textLength=(int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
//
//            x =(gp.screenWidth/2)-(textLength/2);
//            y =gp.screenHeight/2 - (gp.tileSize*3);
//            g2.drawString(text,x,y);
//
//            text="Your time is :"+decimalFormat.format(playTime);
//            textLength=(int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
//
//            x =(gp.screenWidth/2)-(textLength/2);
//            y =gp.screenHeight/2 + (gp.tileSize*4);
//            g2.drawString(text,x,y);
//            gp.thread=null;
//        }else {
//
//
//            if (messageOn) {
//                g2.setFont(g2.getFont().deriveFont(18F));
//                g2.drawString(message, gp.tileSize * 8, gp.tileSize * 3);
//                messageCounter++;
//                if (messageCounter > 120) {
//                    messageCounter = 0;
//                    messageOn = false;
//                }
//            }
//        }
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.black);
        g2.setFont(arial_40);
        g2.setColor(Color.black);
        g2.drawImage(health, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawString(" : " + gp.player.health, 70, 50);
        g2.drawImage(stamina, gp.tileSize / 2, gp.tileSize / 2 + 50, gp.tileSize, gp.tileSize, null);

        playTime+=(double)1/60;
        g2.drawString("time : "+decimalFormat.format(playTime),gp.tileSize*15,65);


        g2.drawString(" : " + gp.player.stamina, 70, 90);
        if (gp.gameState==gp.playState){
            // do play state stuff
        }
        if (gp.gameState==gp.pauseState){
            drawPauseScreen();

        }
    }

    public void drawPauseScreen(){

        String text = "Paused";
        int x= getXForCenterText(text);

        int y=gp.screenHeight/2;
        g2.drawString(text,x,y);

    }

    public int getXForCenterText(String text){
        int length= (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.screenWidth/2-length/2;
        return x;

    }

    public void drawDialogueScreen() {
        //window
        int x=gp.tileSize*2;
        int y=gp.tileSize/2;
        int width=gp.screenWidth-(gp.tileSize*4);
        int height=gp.tileSize*5;
        drawSubWindow(x,y,width,height);

    }


    public void drawSubWindow(int x,int y,int width,int height){
        Color c = new Color(0,0,0);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,25);

    }
}
