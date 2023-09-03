package main;
import Object.Obj_key;
public class AssetSetter {
    GamePanel gp;
    AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){
        gp.obj[0]=new Obj_key();
        gp.obj[0].worldX=23* gp.tileSize;
        gp.obj[0].worldY=43* gp.tileSize;

    }
}
