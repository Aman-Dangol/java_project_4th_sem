package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public  void checkTile(Entity entity){
        int entityLeftWorldX=entity.worldX+entity.solidArea.x;
        int entityRightWorldX=entity.worldX+entity.solidArea.x+entity.solidArea.width;
        int entityTopWorldY=entity.worldY+entity.solidArea.y;
        int entityBottomWorldY=entity.worldY+entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol= entityLeftWorldX/ gp.tileSize;
        int entityRightCol= entityRightWorldX/ gp.tileSize;
        int entityTopRow= entityTopWorldY/gp.tileSize;
        int entityBottomRow= entityBottomWorldY/gp.tileSize;

        int tileNum1,tileNum2;
        switch (entity.direction){
            case "up":
                entityTopRow=(entityTopWorldY+48-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNUm[entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNUm[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
                    System.out.println("up");
                }
                break;
            case "down":
                entityBottomRow=(entityBottomWorldY+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNUm[entityLeftCol][entityBottomRow];
                tileNum2=gp.tileM.mapTileNUm[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
                    System.out.println("down");
                }
                break;
            case "left":
                entityLeftCol=(entityLeftWorldX-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNUm[entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNUm[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
                }
                break;
        }

    }

    }

