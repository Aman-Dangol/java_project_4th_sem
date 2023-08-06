import javax.swing.*;
import java.awt.*;

public class TileManager {


    GamePanel gp;
    public  int backgroundX = 0;
    public int backgroundY = 0;
    public int backgroundImageWidth;
    public  int backgroundImageHeight;
    public Image background;

    public TileManager(GamePanel gp){
        this.gp=gp;
        getTileImage();
        // Get the background image dimensions
        backgroundImageWidth =background.getWidth(null);
        backgroundImageHeight =background.getHeight(null);;


    }


    public void getTileImage(){
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/background/background.png"));
        background = imageIcon.getImage();

    }

    public void draw(Graphics2D g2){
        int offsetX=gp.player.screenX-gp.player.x;
        int offsetY=gp.player.screenY-gp.player.y;
        g2.drawImage(background,backgroundX+offsetX,backgroundY+offsetY,null);
    }






}