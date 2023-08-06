package entity;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    String prevDirection;



    public Player(GamePanel gp,KeyHandler keyH){

        this.gamePanel=gp;
        this.keyH=keyH;

        screenX=gamePanel.width/2-(gp.tileSize/2);
        screenY=gamePanel.height/2-(gp.tileSize/2);


        setDefaultValues();
        getPlayerImage();


    }
    public void setDefaultValues(){
        x=gamePanel.tileSize*20;
        y=gamePanel.tileSize*11;
        speed=4;
        direction="right";
     //   prevDirection="right";

    }
    void getPlayerImage(){
        try {
            up1= ImageIO.read(getClass().getResourceAsStream("/player/up-left-1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/player/up-left-2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/right-1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/player/right-3.png"));
            right3= ImageIO.read(getClass().getResourceAsStream("/player/right-2.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/player/left-3.png"));
            left3= ImageIO.read(getClass().getResourceAsStream("/player/left-2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.up==true|| keyH.down==true|| keyH.left==true|| keyH.right==true) {
            if (keyH.up == true) {
                direction = "up";
                y-= speed;
            }
            if (keyH.down == true) {
                direction = "down";
                y+= speed;
            }
            if (keyH.left == true) {
                direction = "left";
                x -= speed;
            }
            if (keyH.right == true) {
                //direction is for changing img according to the arrow
                direction = "right";
                //helps to increase the x-axis if right key is pressed
                x+= speed;
            }
            //60FPS,so every 11 frame,it change the image
            spriteCounter++;
            //if spritCounter or every 11 frame >11, spritcounter=0 and count again ;
            if (spriteCounter > 11) {
                if (spriteNum == 1) {//default spriteNum is 1
                    spriteNum = 2;
                } else if (spriteNum == 2) {    spriteNum = 3;
                    spriteNum = 3;

                } else if (spriteNum==3) {
                    spriteNum =2;

                }
                spriteCounter = 0;
            }
        }


    }
    void jump(){
        fallingSpeed=jump;
        y+=fallingSpeed;
    }
//    public void falling(){
//        if(y<gamePanel.height-gamePanel.tileSize) {
//            y += fallingSpeed;
//            fallingSpeed++;
//
////        }
//        else {
//            y = gamePanel.height - gamePanel.tileSize;
//        }
//
//    }
    public void draw(Graphics2D g2d){
        BufferedImage image = null;
//        switch (direction){
//            case "up":
//                if (prevDirection=="left"){
//                    image=left1;
//                }else if (prevDirection=="right"){
//                    image=right2;
//                }
//
//                break;
//            case "left":
//                if (spriteNum==1){
//                    image=left1;
//                }else {
//                    image=left2;
//                }
//                prevDirection="left";
//                break;
//            case "right":
//                if (spriteNum==1){
//                    image=right1;
//                }
//                if (spriteNum==2){
//                    image=right2;
//                }
//                prevDirection="right";
//                break;

        switch (direction){
            case "up":
                if(spriteNum==1){
                    image = up1;
                }
                if(spriteNum==2){
                    image = up2;
                }

                break;
            case "down":
                image = right1;
                break;
            case "left":
                if(spriteNum==1){
                    image = left1;
                }
                if(spriteNum==2){
                    image = left2;
                }
                if(spriteNum==3){
                    image=left3;
                }


                break;
            case "right":
                if(spriteNum==1){
                    image = right1;
                }
                if(spriteNum==2){
                    image = right2;
                }
                if(spriteNum==3){
                    image=right3;
                }

                break;



        }
        g2d.drawImage(image,screenX,screenY,gamePanel.tileSize, gamePanel.tileSize, null);
    }


}
