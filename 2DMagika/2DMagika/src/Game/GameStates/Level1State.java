package Game.GameStates;

import java.awt.*;
import Game.Entities.*;
import Mapping.Map;

/**
 * Created by Elemental on 12/10/2016.
 */
public class Level1State extends GameState{
    private Player player;

    private Map map;

    public Level1State(GameStateManager gsm){
        super(gsm);
    }

    public void init() {
        player= new Player(42,34);
        map = new Map("/Maps/map1.map");

        xOffSet= -200;
        yOffset= -400;
    }

    public void tick() {
        player.tick(map.getBlocks(),map.getMovingBlocks());
        map.tick();

    }

    public void draw(Graphics g) {
        player.draw(g);
        map.draw(g);

    }

    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    public void keyReleased(int k) {
        player.keyReleased(k);
    }


}
