package tile;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
        mapTileNUm=new int[gp.maxWorldcol][gp.maxWorldRow];
        getTileImage();
        loadmap("/maps/mapMM.txt");


    }
    public void loadmap(String filePath){
        try{
            InputStream is=getClass().getResourceAsStream(filePath);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            //here also change maxScreenXol and maxScreenRow to maxWorldCol n maxWorldRow
            while(col<gp.maxWorldcol&&row <gp.maxWorldRow) {
                String line = br.readLine();
                while(col<gp.maxWorldcol){
                    String numbers[]=line.split(" ");
                    int num=Integer.parseInt(numbers[col]);

                    if (num!=0){
                        mapTileNUm[col][row]=num;
                    }
                    col++;
                }
                if(col==gp.maxWorldcol){
                    col=0;
                    row++;

                }
            }
        }catch(Exception e){

        }



    }
    public void getTileImage(){
        try{

            tile[0]=new Tile();
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gp.maxWorldcol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNUm[worldCol][worldRow];
            //finding x and y
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.x + gp .player.screenX;
            int screenY = worldY - gp.player.y + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.x - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.x + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.y - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.y + gp.player.screenY
            ) {
                if (worldRow == 12) {
                    System.out.println("world col");
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

                }
            }
            worldCol++;

            if (worldCol == gp.maxWorldcol) {
                worldCol = 0;
                worldRow++;


            }


        }


    }
    }
