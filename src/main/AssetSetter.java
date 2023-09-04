package main;
import Object.Obj_gun;
public class AssetSetter {
    GamePanel gp;
    AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){
        gp.obj[0]=new Obj_gun();
        gp.obj[0].worldX=23* gp.tileSize;
        gp.obj[0].worldY=43* gp.tileSize;

    }
}
