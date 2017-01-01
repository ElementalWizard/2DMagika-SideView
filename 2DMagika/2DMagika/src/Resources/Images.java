package Resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Elemental on 12/19/2016.
 */
public class Images {

    private static final int width = 32, height = 32;

    public static BufferedImage[] blocks;
    public static BufferedImage[] player_right;
    public static BufferedImage[] player_left;
    public static BufferedImage[] player_front;


    private String path;

    public Images() {


        SpriteSheet sheet = new SpriteSheet(Images.loadImage("/Sheets/ss.png"));

        blocks = new BufferedImage[4];
        player_left = new BufferedImage[4];
        player_right = new BufferedImage[4];
        player_front = new BufferedImage[1];



        try {
            //player image
            blocks[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/Slime.png"));

            //block images,array index is equal to block id
            blocks[1] = ImageIO.read(getClass().getResourceAsStream("/Blocks/grass.png"));
            blocks[2] = ImageIO.read(getClass().getResourceAsStream("/Blocks/dirt.png"));
            blocks[3] = ImageIO.read(getClass().getResourceAsStream("/Blocks/MovingTile.png"));

            //player anim
            player_front[0]=sheet.crop(0,0,width,height);


            player_right[0]=sheet.crop(0,64,width,height);
            player_right[1]=sheet.crop(33,64,width,height);
            player_right[2]=sheet.crop(65,64,width,height);
            player_right[3]=sheet.crop(97,64,width-1,height);

            player_left[0]=sheet.crop(0,32,width,height);
            player_left[1]=sheet.crop(33,32,width,height);
            player_left[2]=sheet.crop(65,32,width,height);
            player_left[3]=sheet.crop(97,32,width-1,height);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }



}
