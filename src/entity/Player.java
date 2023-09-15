package entity;
import main.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    KeyHandler keyH;


    public  int screenX;
    public  int screenY;

    public  int Stamina =200;

    public int maxStamina =200;


    public boolean hasGun=false;

    public int killCount= 0;




    public double angle;
    Weapon weapon = new Weapon(this);
    public Player(GamePanel gp,KeyHandler keyH){
        super(gp);
        type =0;

        this.keyH=keyH;
        maxHealth =10;
        health=maxHealth;

        screenX= gp.screenWidth/2;
        screenY=gp.screenHeight/2;

        solidArea = new Rectangle(0,0,gp.tileSize,gp.tileSize);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY=solidArea.y;


        setDefaultValues();
        getPlayerImage();
        direction="down";


    }
    public void setDefaultValues(){
       worldX =gp.tileSize*23;
        worldY =gp.tileSize*23;
        speed=4;

    }
    void getPlayerImage(){
        upLeft1= setup("/player/up_left_1");
        upLeft2=setup("/player/up_left_2") ;
        upRight1=setup("/player/up_right_1") ;
        upRight2= setup("/player/up_right_1");
        right1= setup("/player/right_1");
        right2= setup("/player/right_2");
        right3= setup("/player/right_3");
        left1= setup("/player/left1");
        left2= setup("/player/left_2");
        left3=setup("/player/left_3");
        fly=setup("/player/fly");
    }



    public void update()  {
        double dx= gp.mouseH.X- weapon.centerX;
        double dy=gp.mouseH.Y- weapon.centerY;
        angle=Math.atan2(dy,dx);
            if (keyH.upPressed) {
                direction = "up";

            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";
                //direction is for changing img according to the arrow
                //helps to increase the x-axis if right key is pressed
            }
            collisionOn=false;
            gp.collisionChecker.checkTile(this);
        int objIndex=gp.collisionChecker.checkObject(this,true);
            if(keyH.pick){
                pickup(objIndex);
            }

            int enemyIndex = gp.collisionChecker.checkEntity(this,gp.enemy);

            contactEnemy(enemyIndex);
        if (!collisionOn) {
                if (keyH.upPressed) {
                    jump();
                }
                if (keyH.downPressed) {
                        worldY += speed;

                }
                if (keyH.leftPressed) {
                        worldX -= speed;
                }
                if (keyH.rightPressed) {
                    worldX += speed;
                    //direction is for changing img according to the arrow
                    //helps to increase the x-axis if right key is pressed
                }

            }




            //60FPS,so every 11 frame,it changes the image
            spriteCounter++;
            //if spriteCounter or every 11 frame >11, spriteCounter=0 and count again ;
        if (keyH.downPressed || keyH.rightPressed || keyH.leftPressed || keyH.upPressed){
            if (spriteCounter > 12) {
                if (spriteNum == 1) {//default spriteNum is 1
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 2;
                }
                spriteCounter = 0;
            }
        }
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter >60){
                invincible=false;
                invincibleCounter =0;
            }
        }
    }


    void jump()  {
        if (Stamina >0){
            fallingSpeed=jump;
            worldY +=fallingSpeed;
        }
    }
    public void falling()  {

        if (gp.collisionChecker.checkFall(this) && fall){
            worldY += fallingSpeed;
            fallingSpeed++;
            if (fallingSpeed>16){
                fallingSpeed=15;
            }
        }
        else {
            float point=worldY/12;
            int onlyWhole= (int) point;
            worldY=onlyWhole*12;
        }
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = null;
        switch (direction){
            case "up":

                if(prevDirection=="left") {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left1;
                    }
                    if (spriteNum == 3) {
                        image = left1;
                    }
                } else if (prevDirection=="right") {
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right1;
                        }
                        if (spriteNum == 3) {
                            image = right1;
                        }
                }
                break;
            case "down":
                image = right1;
                break;
            case "left":
                if (!keyH.upPressed){
                    if(spriteNum==1){
                        image = left1;
                    }
                    if(spriteNum==2){
                        image = left2;
                    }
                    if(spriteNum==3){
                        image=left3;
                    }
                }
                else {
                    image=left1;
                }
                break;
            case "right":
                if (!keyH.upPressed){
                    if(spriteNum==1){
                        image = right1;
                    }
                    if(spriteNum==2){
                        image = right2;
                    }
                    if(spriteNum==3){
                        image=right3;
                    }
                }
                else {
                    image=right1;
                }
                break;
        }

        if (health == 0 ){
            gp.gameState = gp.gameOverState;
        }

        //set transparency
        if (invincible){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3F));

        }
        g2d.drawImage(image,screenX,screenY, null);


        //reset transparency
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1F));



        if (keyH.upPressed){

                if (Stamina >0){
                    Stamina -=2;
                    g2d.drawImage(fly,screenX,screenY+48, null);

                }
                else
                    Stamina =0;
        }
        else
        {
            if (Stamina <maxStamina)
            Stamina++;
        }
        weapon.drawWeapon(g2d,gp);


    }


    public void pickup(int i){
        if (i!=999){
            String objectName=gp.obj[i].name;
            switch (objectName){

                case "gun":
                    hasGun=true;
                    weapon.gunRight =gp.obj[i].rightImage;
                    weapon.gunLeft=gp.obj[i].leftImage;
                    gp.obj[i]=null;
                    gp.ui.showMessage("you have a gun");
            }
        }

    }
    public  boolean checkGun(){
        if (hasGun)
            return true;
        else
            return false;

    }
    public void shoot(){
        if (checkGun()) {
//            gp.soundSE(1);
        }
    }
    public void contactEnemy(int enemyIndex){
        if (enemyIndex!=999){
            if (invincible==false){
                health-=10;
                invincible=true;
            }
        }
    }


    public void reset(){
        worldX =gp.tileSize*23;
        worldY =gp.tileSize*23;
        speed=4;
        weapon.gunLeft=null;
        weapon.gunRight=null;
        health = maxHealth;
        invincible=false;
    }



}
