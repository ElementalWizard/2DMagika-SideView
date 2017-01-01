package Game.Objects;

import Game.GameStates.GameState;
import Resources.Images;

import java.awt.*;

/**
 * Created by Elemental on 12/11/2016.
 */
public class Block extends Rectangle{
    private static final long serialVersionUID = 1L;
    public static final int blocksize = 64;
    private int id;




    public Block(int x, int y,int id){
        setBounds(x,y,blocksize,blocksize);
        this.id=id;
    }

    public void tick(){

    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        if(id == 1){
            g.drawImage(Images.blocks[id],x - (int)GameState.xOffSet,y - (int)GameState.yOffset ,width,height,null);
        }else if(id == 2){
            g.drawImage(Images.blocks[id],x - (int)GameState.xOffSet,y - (int)GameState.yOffset ,width,height,null);
        }else if(id == 3){
            g.drawImage(Images.blocks[id],x - (int)GameState.xOffSet,y - (int)GameState.yOffset ,width,height,null);
        }


    }


    public void setID(int id){
        this.id=id;
    }

    public int getID(){
        return id;
    }



}
