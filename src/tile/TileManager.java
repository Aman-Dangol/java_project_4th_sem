package tile;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {


    GamePanel gp;
    public  Tile[] tile;
    public  int mapTileNum[][];
    KeyHandler keyH;

    public int index=0;



    public TileManager(GamePanel gp,KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;
        tile=new Tile[15];
        mapTileNum =new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/mapMM.txt");


    }
    public void loadMap(String filePath){
        try{
            InputStream is=getClass().getResourceAsStream(filePath);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            //here also change maxScreenXol and maxScreenRow to maxWorldCol n maxWorldRow
            while(col<gp.maxWorldCol &&row <gp.maxWorldRow) {
                String line = br.readLine();
                while(col<gp.maxWorldCol){
                    String numbers[]=line.split(" ");
                    int num=Integer.parseInt(numbers[col]);
                        mapTileNum[col][row]=num;

                    col++;
                }
                if(col==gp.maxWorldCol){
                    col=0;
                    row++;

                }
            }
        }catch(Exception e){

        }



    }
    public void getTileImage(){
            setup(0,"transparentTile",false);
            setup(1,"grass",true);
            setup(2,"ground",true);
            setup(3,"transparentTile",true);
            setup(4,"corner_grass",true);
            setup(5,"right_corner_piece",true);
            setup(6,"left_straight",true);
            setup(7,"right_straight",true);
            ;setup(8,"bottom_left",true);
            setup(9,"bottom_right",true);



    }

    void setup(int index,String imageName,boolean collision){
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index]=new Tile();
            tile[index].image= ImageIO.read(getClass().getResourceAsStream("/Tiles/"+imageName+".png"));
            tile[index].image=uTool.scaledImage(tile[index].image,gp.tileSize,gp.tileSize);
            tile[index].collision=collision;


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            //finding x and y
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp .player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
            ) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY,null);


            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;


            }
        }
    }
}

