package Game.GameStates;

import java.awt.*;

/**
 * Created by Elemental on 12/10/2016.
 */
public abstract class GameState {
    protected GameStateManager gsm;
    public static double xOffSet, yOffset;


    public GameState(GameStateManager gsm){
        this.gsm=gsm;

        this.xOffSet=0;
        this.yOffset=0;

        init();
    }

    public abstract void init();
    public abstract void tick();
    public abstract void draw(Graphics g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);

}
