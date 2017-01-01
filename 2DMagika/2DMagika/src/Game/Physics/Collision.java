package Game.Physics;


import Game.Objects.Block;
import Game.Objects.MovingBlock;

import java.awt.*;

/**
 * Created by Elemental on 12/11/2016.
 */
public class Collision {
    public static boolean playerBlock(Point p, Block b){
        return b.contains(p);
    }

    public static boolean playerMovingBlock(Point p, MovingBlock b){
        return b.contains(p);
    }

}
