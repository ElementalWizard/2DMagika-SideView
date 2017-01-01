package Game.Objects;

import Game.GameStates.GameState;
import Resources.Images;

import java.awt.*;

/**
 * Created by Elemental on 12/19/2016.
 */
public class MovingBlock extends Rectangle{
    private static final long serialVersionUID = 1L;

    //where they bounce
    private int leftBound,rightBound;
    private int move = 1;
    private int id;


    public MovingBlock(int x,int y,int id,int leftBound,int rightBound){

        setBounds(x,y,Block.blocksize,Block.blocksize);
        this.id = id;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    public void tick(){

        if(x+width- GameState.xOffSet >= rightBound - GameState.xOffSet && move != -1){
            move *= -1;
        }
        if(x-GameState.xOffSet <= leftBound-GameState.xOffSet&&move != 1){
            move *=-1;
        }
        x+=move;

    }
    public void draw(Graphics g){
        if (id == 3){
            g.drawImage(Images.blocks[id],x-(int)GameState.xOffSet,y -(int)GameState.yOffset,width,height,null);

        }


    }
    public int getMove(){
        return move;
    }
    public int getID(){
        return id;
    }







}
