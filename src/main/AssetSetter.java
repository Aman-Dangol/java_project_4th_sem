package main;
import Enemy.Enemy1;
import Object.Obj_gun;
public class AssetSetter {
    GamePanel gp;
    AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){
        
        gp.obj[0]=new Obj_gun(gp);
        gp.obj[0].worldX=23* gp.tileSize;
        gp.obj[0].worldY=43* gp.tileSize;

    }
    public void setEnemy(int enemyNum){
        for (int i = 0; i < enemyNum; i++) {
            gp.enemy[i] = new Enemy1(gp);
            gp.enemy[i].worldX = gp.tileSize*27;
            gp.enemy[i].worldY = gp.tileSize*42;

        }




    }
}
