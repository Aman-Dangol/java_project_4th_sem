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
    public void setEnemy(){
        gp.enemy[0] = new Enemy1(gp);
        gp.enemy[0].worldX = gp.tileSize*27;
        gp.enemy[0].worldY = gp.tileSize*42;


        gp.enemy[1] = new Enemy1(gp);
        gp.enemy[1].worldX = gp.tileSize*29;
        gp.enemy[1].worldY = gp.tileSize*42;

    }
}
