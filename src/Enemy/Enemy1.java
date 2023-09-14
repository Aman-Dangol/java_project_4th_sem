package Enemy;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.util.Random;


public class Enemy1 extends Entity {
    GamePanel gp ;
    public Enemy1(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name ="bot";
        speed = 5;
        type=2;
        maxHealth =10;
        direction="down";
        health = maxHealth;
        solidArea = new Rectangle(0,0,gp.tileSize,gp.tileSize);
        getImage();


    }

    @Override
    public void update() {
        setAction();
        collisionOn=false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this,false);
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);
        if (!collisionOn){

            switch (direction){
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left" :
                    worldX -= speed;
                    break;
                case "right":
                    worldX+= speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {//player image changes every 10 frames
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;

            }
            spriteCounter = 0;
        }
    }

    public void getImage(){
        right1= setup("/Enemy/bot");
        right2=setup("/Enemy/bot");
        right3=setup("/Enemy/bot");
        upRight1=setup("/Enemy/bot");
        upRight2=setup("/Enemy/bot");
        fly=setup("/Enemy/bot");
        left1=setup("/Enemy/bot_left");
        left2=setup("/Enemy/bot_left");
        left3=setup("/Enemy/bot_left");
        upLeft1=setup("/Enemy/bot_left");
        upLeft2=setup("/Enemy/bot_left");
        upLeft3=setup("/Enemy/bot_left");

    }
    public void setAction(){
        actionLockCounter++;
        if (actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;//pick a number from 1 to 100;
            if (i<=25){
                direction="up";
            }
            if (i>25 && i<=50 ){
                direction="down";

            }
            if (i>50 && i<=70 ){
                direction="left";

            }
            if (i>75 && i<=100 ){
                direction="right";

            }
            actionLockCounter=0;

        }
    }




}
