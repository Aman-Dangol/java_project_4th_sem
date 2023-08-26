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

    int tileNum1,tileNum2;

    Entity entity;
    CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public  void checkTile(Entity entity){
        this.entity = entity;
         entityLeftWorldX=entity.worldX+entity.solidArea.x;
         entityRightWorldX=entity.worldX+entity.solidArea.x+entity.solidArea.width;
         entityTopWorldY=entity.worldY+entity.solidArea.y;
         entityBottomWorldY=entity.worldY+entity.solidArea.y+entity.solidArea.height;

         entityLeftCol= entityLeftWorldX/ gp.tileSize;
         entityRightCol= entityRightWorldX/ gp.tileSize;
         entityTopRow= entityTopWorldY/gp.tileSize;
         entityBottomRow= entityBottomWorldY/gp.tileSize;

        int tileNum1,tileNum2;



        switch (entity.direction){
            case "up":
                entityTopRow=(entityTopWorldY-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision==true  || gp.tileM.tile[tileNum2].collision==true ){
                    entity.collisionOn=true;
                }
                break;
            case "down":
                entityBottomRow=(entityBottomWorldY+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                entityLeftCol=(entityLeftWorldX-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    System.out.println("left");

                    entity.collisionOn=true;
                }
                break;
            case "right":
                entityRightCol=(entityRightWorldX+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    System.out.println("right");
                    entity.collisionOn=true;
                }
                break;
        }

    }

    }

