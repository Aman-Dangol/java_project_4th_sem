package main;
import Enemy.Enemy1;
import Object.Obj_gun;

import java.util.Random;

public class AssetSetter {
    GamePanel gp;
    Random random = new Random();
    AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){
        
        gp.obj[0]=new Obj_gun(gp);
        gp.obj[0].worldX=23* gp.tileSize;
        gp.obj[0].worldY=11* gp.tileSize;

    }
    public void setEnemy(int enemyNum){
        for (int i = 0; i < enemyNum; i++) {
            gp.enemy[i] = new Enemy1(gp);
            boolean place= false;
            int x=0,y=0;
            while (!place){
                x = random.nextInt(0,gp.maxWorldCol-1);
                y = random.nextInt(0,gp.maxWorldRow-1);
                int tileNum = gp.tileM.mapTileNum[x][y];
                if (!gp.tileM.tile[tileNum].collision){
                    place=true;
                }

            }
            gp.enemy[i].worldX = gp.tileSize*x;
            gp.enemy[i].worldY = gp.tileSize*y;
            System.out.println(gp.enemy[i].worldX +" "+gp.enemy[i].worldY);
        }


    }
}
