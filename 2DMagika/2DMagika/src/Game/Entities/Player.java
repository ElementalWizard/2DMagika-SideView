package Game.Entities;

import Game.GameStates.GameState;
import Game.Objects.Block;
import Game.Objects.MovingBlock;
import Game.Physics.Collision;
import Resources.Animation;
import Resources.Images;
import Resources.SpriteSheet;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import static java.awt.event.KeyEvent.*;


/**
 * Created by Elemental on 12/10/2016.
 */
public class Player {


    private boolean right = false;
    private boolean left = false;
    private boolean jumping = false;
    private boolean falling = false;

    private boolean topCollision = false;

    private double x;
    private double y;
    private int width;
    private int height;


    private double moveSpeed = 2.5;


    private double JumpSpeed = 5;
    private double currentJumpSpeed = JumpSpeed;


    private double maxFallSpeed = 5;
    private double currentFallSpeed = 0.1;




    private Animation anmRight;
    private Animation anmLeft;

    public Player(int height, int width) {
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;
        this.width = width;
        this.height = height;

        anmRight = new Animation(350, Images.player_right);
        anmLeft = new Animation(350, Images.player_left);

    }

    public void tick(Block[][] b, ArrayList<MovingBlock> movingBlocks) {

        anmRight.tick();
        anmLeft.tick();
        int iX = (int) x;
        int iY = (int) y;

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {

                if (b[i][j].getID() != 0) {

                    //right side of player check
                    if (Collision.playerBlock(new Point(iX + width + (int) GameState.xOffSet, iY + (int) GameState.yOffset + 2), b[i][j])
                            || Collision.playerBlock(new Point((iX + width + (int) GameState.xOffSet), (iY + height + (int) GameState.yOffset - 1)), b[i][j])) {
                        right = false;
                    }

                    //left side
                    if (Collision.playerBlock(new Point(iX + (int) GameState.xOffSet - 1, iY + (int) GameState.yOffset + 2), b[i][j])
                            || Collision.playerBlock(new Point((iX + (int) GameState.xOffSet - 1), (iY + height + (int) GameState.yOffset - 1)), b[i][j])) {
                        left = false;
                    }
                    //topside
                    if (Collision.playerBlock(new Point(iX + (int) GameState.xOffSet + 1, iY + (int) GameState.yOffset), b[i][j])
                            || Collision.playerBlock(new Point((iX + width + (int) GameState.xOffSet - 2), (iY + (int) GameState.yOffset)), b[i][j])) {
                        jumping = false;
                        falling = true;
                    }

                    //bottom
                    if (Collision.playerBlock(new Point(iX + width + (int) GameState.xOffSet - 2, iY + height + (int) GameState.yOffset + 1), b[i][j])
                            || Collision.playerBlock(new Point((iX + (int) GameState.xOffSet + 2), (iY + height + (int) GameState.yOffset + 1)), b[i][j])) {
                        y = b[i][j].getY() - height - GameState.yOffset;
                        falling = false;
                        topCollision = true;
                    } else {
                        if (!topCollision && !jumping) {
                            falling = true;
                        }
                    }
                }
            }
        }


        for (int i = 0; i < movingBlocks.size(); i++) {
            if (movingBlocks.get(i).getID() != 0) {
                //right side of player check
                if (Collision.playerMovingBlock(new Point(iX + width + (int) GameState.xOffSet, iY + (int) GameState.yOffset + 2), movingBlocks.get(i))
                        || Collision.playerMovingBlock(new Point((iX + width + (int) GameState.xOffSet), (iY + height + (int) GameState.yOffset - 1)), movingBlocks.get(i))) {
                    right = false;
                }

                //left side
                if (Collision.playerMovingBlock(new Point(iX + (int) GameState.xOffSet - 1, iY + (int) GameState.yOffset + 2), movingBlocks.get(i))
                        || Collision.playerMovingBlock(new Point((iX + (int) GameState.xOffSet - 1), (iY + height + (int) GameState.yOffset - 1)), movingBlocks.get(i))) {
                    left = false;
                }
                //topside
                if (Collision.playerMovingBlock(new Point(iX + (int) GameState.xOffSet - 2, iY + (int) GameState.yOffset - 1), movingBlocks.get(i))
                        || Collision.playerMovingBlock(new Point((iX + width + (int) GameState.xOffSet + 2), (iY + (int) GameState.yOffset - 1)), movingBlocks.get(i))) {
                    jumping = false;
                    falling = true;
                }

                //bottom
                if (Collision.playerMovingBlock(new Point(iX + width + (int) GameState.xOffSet + 2, iY + height + (int) GameState.yOffset + 2), movingBlocks.get(i))
                        || Collision.playerMovingBlock(new Point((iX + (int) GameState.xOffSet - 2), (iY + height + (int) GameState.yOffset + 2)), movingBlocks.get(i))) {
                    y = movingBlocks.get(i).getY() - height - GameState.yOffset;
                    falling = false;
                    topCollision = true;

                    GameState.xOffSet += movingBlocks.get(i).getMove();

                } else {
                    if (!topCollision && !jumping) {
                        falling = true;
                    }
                }
            }
        }

        topCollision = false;


        if (right) {

            GameState.xOffSet += moveSpeed;
        }
        if (left) {

            GameState.xOffSet -= moveSpeed;
        }
        if (jumping) {
            GameState.yOffset -= currentJumpSpeed;
            currentJumpSpeed -= .1;

            if (currentJumpSpeed <= 0) {
                currentJumpSpeed = JumpSpeed;
                jumping = false;
                falling = true;
            }
        }
        if (!falling) {
            currentFallSpeed = .1;
        }

        if (falling) {
            GameState.yOffset += currentFallSpeed;
            if (currentFallSpeed < maxFallSpeed) {
                currentFallSpeed += .1;
            }
        }

    }

    public void draw(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(),(int)x,(int)y,width,height,null);

    }

    private BufferedImage getCurrentAnimationFrame(){
        if(left){
            return anmLeft.getCurrentFrame();
        }else if (right){
            return anmRight.getCurrentFrame();
        }else{
            return Images.player_front[0];

        }

    }


    public void keyPressed(int k){

        if (k == VK_D) right = true;
        if (k == VK_A) left = true;
        if (k == VK_SPACE && !jumping && !falling) jumping = true;

    }

    public void keyReleased(int k){

        if (k == VK_D) {
            right = false;
        }
        if (k == VK_A) {
            left = false;
        }

    }

}
