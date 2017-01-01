package Game.GameStates;

import java.awt.*;
import java.util.Stack;

/**
 * Created by Elemental on 12/10/2016.
 */
public class GameStateManager {

    public Stack<GameState> states;

    public GameStateManager() {
        states = new Stack<GameState>();
        states.push(new MenuState(this));
    }
    public void tick(){
        states.peek().tick();
    }

    public void draw(Graphics g){
        states.peek().draw(g);
    }

    public void keyPressed(int k){
        states.peek().keyPressed(k);
    }

    public void KeyReleased(int k){
        states.peek().keyReleased(k);
    }
}
