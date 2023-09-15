package main;

import entity.Bullet;
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

            if (entityTopRow >= 0 && entityTopRow < gp.tileM.mapTileNum.length &&
                    entityLeftCol >= 0 && entityLeftCol < gp.tileM.mapTileNum[0].length &&
                    entityRightCol >= 0 && entityRightCol < gp.tileM.mapTileNum[0].length) {
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            }
        }
        if (entity.direction == "down") {

            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;

            if (entityBottomRow >= 0 && entityBottomRow < gp.tileM.mapTileNum.length &&
                    entityLeftCol >= 0 && entityLeftCol < gp.tileM.mapTileNum[0].length &&
                    entityRightCol >= 0 && entityRightCol < gp.tileM.mapTileNum[0].length) {
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            }
        }
        if (entity.direction == "left") {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;

            if (entityTopRow >= 0 && entityTopRow < gp.tileM.mapTileNum.length &&
                    entityLeftCol >= 0 && entityLeftCol < gp.tileM.mapTileNum[0].length ) {
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            }
        }
        if (entity.direction == "right") {
            entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;

            if (entityTopRow >= 0 && entityTopRow < gp.tileM.mapTileNum.length  &&
                    entityRightCol >= 0 && entityRightCol < gp.tileM.mapTileNum[0].length) {
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            }
        }


    }

    public boolean checkFall(Entity entity) {
        int  entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
      int  entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;


        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;

        int entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
        int tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
        int tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
        if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
            return false;
        } else
        {
            return true;
        }

    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                //get entity's solid area position


                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get object's solid area position

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;

                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                    if (gp.obj[i].collision == true) {
                        entity.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;

    }

    //npc collision check
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;
        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                //get entity's solid area position


                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get object's solid area position

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            if (target[i]!=entity){
                                entity.collisionOn = true;
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += gp.tileSize;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            if (target[i]!=entity){
                                System.out.println("false");
                            }
                            entity.fall = false;

                        }
                        else {
                            entity.fall = true;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            if (target[i]!=entity){
                                entity.collisionOn = true;
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            if (target[i]!=entity){
                                entity.collisionOn = true;
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;

    }



    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer=false;

        //get entity's solid area position


        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        //get object's solid area position

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }

        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer=true;

        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        return contactPlayer;

    }

    public void checkBulletCollision(Bullet entity) {
        int x=gp.tileM.mapTileNum[(entity.worldX/48)][(entity.worldY/48)];
        if (gp.tileM.tile[x].collision==true){
            entity.collisionOn=true;
        }

    }
    public void shotCollision(Entity entity , Entity[] targets){

        entity.solidArea.x=entity.worldX;
        entity.solidArea.y=entity.worldY;


        for (int i = 0; i < targets.length; i++) {
            if (targets[i]!=null){
                //get object's solid area position

                targets[i].solidArea.x=targets[i].worldX+targets[i].solidArea.x;
                targets[i].solidArea.y=targets[i].worldY+targets[i].solidArea.y;
                if ( targets[i]!= null && entity.solidArea.intersects(targets[i].solidArea)){
                        targets[i].contactEnemy();
                        if (targets[i].health<=0){
                            targets[i]=null;
                            gp.player.killCount++;
                        }

                }
            }

        }

    }
}

