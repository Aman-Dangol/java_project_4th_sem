package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI   {
    GamePanel gp;
    Font arial_40 , arial_80B;

    public boolean messageOn=false;
    public String message="";

    public int commandNum=0;

    public int messageCounter=0;

    public Graphics2D g2;
    BufferedImage health,stamina;

    double playTime;


    UtilityTool utilityTool = new UtilityTool();
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    public  UI(GamePanel gp){
        this.gp=gp;
        arial_40 = new Font("Arial",Font.PLAIN,20);
        arial_80B = new Font("Arial",Font.BOLD,80);
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
        this.g2 = g2;


        g2.setFont(arial_40);
        g2.setColor(Color.black);
        g2.setFont(arial_40);
        g2.setColor(Color.black);


        if (gp.gameState==gp.titleState){
            drawTitleScreen();
        }
        else if (gp.gameState==gp.playState){
            playGame();
        }
        else if (gp.gameState==gp.pauseState){
            drawPauseScreen();
        }
       else if (gp.gameState==gp.gameOverState){
            drawGameOverScreen();
        }
    }

    public void playGame(){
        gp.stopMusic();
        g2.drawImage(health, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawString(" : " + healthCalculate(gp.player.health), 70, 50);
        g2.drawImage(stamina, gp.tileSize / 2, gp.tileSize / 2 + 50, gp.tileSize, gp.tileSize, null);
        g2.drawString(" : " + gp.player.Stamina, 70, 90);
        g2.drawString(" bullets : " + (gp.bullet.length-gp.bulletIndex), 70, 120);
        playTime+=(double)1/60;
        g2.drawString("time : "+decimalFormat.format(playTime),gp.tileSize*15,65);
        g2.drawString("enemies : "+enemyCounter(),gp.tileSize*15,85);
        g2.drawString("kills : "+gp.player.killCount,gp.tileSize*15,105);
        // do play state stuff

        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(18F));
            g2.drawString(message, gp.tileSize * 8, gp.tileSize * 3);
            messageCounter++;
            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
    public void drawGameOverScreen(){
        g2.setColor(Color.black);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        int x;
        int y;
        String text ="Game Over";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110F));
        g2.setColor(Color.white);
        x= getXForCenterText(text);
        y=gp.tileSize*4;
        g2.drawString(text,x,y);
        //kill count
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
        text = "kills :"+gp.player.killCount;
        g2.setColor(Color.white);
        x= getXForCenterText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);

        // playtime

        text = "play Time :"+decimalFormat.format(playTime);
        g2.setColor(Color.white);
        x= getXForCenterText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);

        //play again
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        g2.setColor(Color.white);
        text="Play again";
        x= getXForCenterText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if (commandNum == 0){
            g2.drawString(">",x-40,y);
        }

        //main menu
        g2.setColor(Color.white);
        text="Main menu";
        x= getXForCenterText(text);
        y+=60;
        g2.drawString(text,x,y);
        if (commandNum == 1){
            g2.drawString(">",x-40,y);
        }

    }

    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));


        String text = "Paused";
        int x= getXForCenterText(text);
        int y=gp.tileSize * 2;
        g2.drawString(text,x,y);

        text= "Resume";
        x= getXForCenterText(text);
        y+=gp.tileSize*4;
        g2.setColor(Color.black);

        g2.drawString(text,x,y);if (commandNum == 0){
            g2.drawString(">",x-gp.tileSize,y);
        }

        text= "Main Menu";
        x= getXForCenterText(text);
        y+=gp.tileSize;
        g2.setColor(Color.black);
        g2.drawString(text,x,y);if (commandNum == 1){
            g2.drawString(">",x-gp.tileSize,y);
        }
    }




    public int getXForCenterText(String text){
        int length= (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.screenWidth/2-length/2;
        return x;

    }

    public int healthCalculate(int health){
        if (health<0){
            health=0;
        }
        return health;

    }

    public int enemyCounter(){
        int enemyNum =0;
        for (int i = 0;i<gp.enemy.length;i++){
            if (gp.enemy[i]!=null){
                enemyNum++;
            }
        }
        return enemyNum;
    }


    public void drawTitleScreen(){

        int x=0,y=0;
        String play,quit;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
        g2.setColor(Color.white);
        String instructions = "w,a,s,d -> move\n e-> pickup Gun r-> reload left click-> shoot ESC -> pause  enter->select";
         x = getXForCenterText(instructions);
         y=gp.tileSize;
        g2.drawString(instructions,x,y);
//
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));

        play= "New Game";
        x= getXForCenterText(play);
        y+=gp.tileSize*9;
        g2.setColor(Color.black);
        g2.drawString(play,x+2,y+2);

        g2.setColor(Color.GREEN.darker());
        g2.drawString(play,x,y);if (commandNum == 0){
            g2.drawString(">",x-gp.tileSize,y);
        }

        quit= "Quit";
        x= getXForCenterText(quit);
        y+=gp.tileSize;
        g2.setColor(Color.black);
        g2.drawString(quit,x+4,y+4);
        g2.setColor(Color.GREEN.darker());
        g2.drawString(quit,x,y);if (commandNum == 1){
            g2.drawString(">",x-gp.tileSize,y);
        }
    }

}
