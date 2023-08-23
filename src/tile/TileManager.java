package tile;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {


    GamePanel gp;
    public  Tile[] tile;
    public  int mapTileNUm[][];
    KeyHandler keyH;

    public Image background;



    public TileManager(GamePanel gp,KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;
        tile=new Tile[10];
        mapTileNUm=new int[gp.maxWorldCol][gp.maxWorldRow];
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

                    if (num!=0){
                        mapTileNUm[col][row]=num;
                    }
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
        try{

            tile[1]=new Tile();
            tile[1].image= ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[2]=new Tile();
            tile[2].image= ImageIO.read(getClass().getResourceAsStream("/tiles/ground.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNUm[worldCol][worldRow];
            //finding x and y
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.x + gp .player.screenX;
            int screenY = worldY - gp.player.y + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.x - gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.y - gp.player.screenY
            ) {
                if (tileNum!=0) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    tile[tileNum].tileX=worldX;
                    tile[tileNum].tileY=worldY;


                }
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;


            }
        }
    }
}

