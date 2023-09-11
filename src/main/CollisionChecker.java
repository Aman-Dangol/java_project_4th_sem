package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    int entityLeftWorldX;
    int entityRightWorldX;
    int entityTopWorldY;
    int entityBottomWorldY;

    int entityLeftCol;
    int entityRightCol;
    int entityTopRow;
    int entityBottomRow;
    int tileNum1, tileNum2;


    Entity entity;

    CollisionChecker(GamePanel gp) {
        this.gp = gp;

    }

    public void checkTile(Entity entity) {
        this.entity = entity;
        entityLeftWorldX = entity.worldX + entity.solidArea.x;
        entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        entityTopWorldY = entity.worldY + entity.solidArea.y;
        entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        entityLeftCol = entityLeftWorldX / gp.tileSize;
        entityRightCol = entityRightWorldX / gp.tileSize;
        entityTopRow = entityTopWorldY / gp.tileSize;
        entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;


        if (entity.direction == "up") {
            entityTopRow = ((entityTopWorldY - entity.speed) / gp.tileSize);
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
        }
        if (entity.direction == "down") {
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
        }
        if (entity.direction == "left") {
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
        }
        if (entity.direction == "right") {
            entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
        }


    }

    public boolean checkFall(Entity entity) {
        entityLeftWorldX = entity.worldX + entity.solidArea.x;
        entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        entityLeftCol = entityLeftWorldX / gp.tileSize;
        entityRightCol = entityRightWorldX / gp.tileSize;

        entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
        tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
//        System.out.println(entityBottomRow);
        if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
            return false;
        } else
        {
            return true;
        }

    }

    public int checkObject(Entity entity,boolean player){

        int index= 999;
        for (int i=0;i<gp.obj.length;i++){
            if (gp.obj[i]!=null){
                //get entity's solid area position


                entity.solidArea.x=entity.worldX+entity.solidArea.x;
                entity.solidArea.y=entity.worldY+entity.solidArea.y;

                //get object's solid area position

                gp.obj[i].solidArea.x=gp.obj[i].worldX+gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y=gp.obj[i].worldY+gp.obj[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y-=entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if (gp.obj[i].collision==true){
                                entity.collisionOn=true;
                            }
                            if (player==true){
                                index=i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y+=entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if (gp.obj[i].collision==true){
                                entity.collisionOn=true;
                            }
                            if (player==true){
                                index=i;

                            }
                        }

                        break;
                    case "left":
                        entity.solidArea.x-=entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)){

                            if (gp.obj[i].collision==true){
                                entity.collisionOn=true;
                            }
                            if (player==true){
                                index=i;

                            }
                        }

                        break;
                    case "right":
                        entity.solidArea.x+=entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if (gp.obj[i].collision==true){
                                entity.collisionOn=true;
                            }
                            if (player==true){
                                index=i;

                            }
                        }
                        break;
                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x=gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y=gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;

    }
}

