package Enemy;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Enemy1 extends Entity {
    public Enemy1(GamePanel gp) {
        super(gp);
        name ="bot";
        speed = 5;
        type=2;
        maxHealth =10;
        direction="left";
        health = maxHealth;
        getImage();

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
