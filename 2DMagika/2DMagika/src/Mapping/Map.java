package Mapping;

import Game.Objects.Block;
import Game.Objects.MovingBlock;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Elemental on 12/13/2016.
 */
public class Map {

    private String path;
    private String line;

    private int width,height;
    private Block[][] blocks;
    private ArrayList<MovingBlock> movingBlocks;

    public Map(String loadpath){

        path = loadpath;

        loadMap();
    }

    public void draw(Graphics g){

        for(int i = 0;i<blocks.length;i++){
            for(int j=0;j<blocks[0].length;j++){
                blocks[i][j].draw(g);
            }
        }

        for(int i = 0; i< movingBlocks.size();i++){
            movingBlocks.get(i).draw(g);
        }


    }
    public void tick(){
        for(int i = 0; i<movingBlocks.size();i++){
            movingBlocks.get(i).tick();
        }
    }

    public void loadMap(){
        InputStream is = this.getClass().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {
            width = Integer.parseInt(br.readLine());
            height = Integer.parseInt(br.readLine());

            blocks = new Block[height][width];


            for(int y = 0;y<height;y++) {
                line = br.readLine();
                String[] tokens = line.split("\\s+");
                for(int x = 0;x<width;x++) {
                    blocks[y][x] = new Block(x*Block.blocksize,y*Block.blocksize,Integer.parseInt(tokens[x]));
                }
            }

            //now we add the moving blocks
            line=br.readLine();
            line=br.readLine();
            int length = Integer.parseInt(line);
            movingBlocks = new ArrayList<MovingBlock>();

            for(int i = 0;i<length;i++){
                line = br.readLine();
                String[] tokens = line.split("\\s+");

                movingBlocks.add(new MovingBlock(Integer.parseInt(tokens[0])*Block.blocksize,
                        Integer.parseInt(tokens[1])*Block.blocksize,Integer.parseInt(tokens[2]),
                        Integer.parseInt(tokens[3])*Block.blocksize,Integer.parseInt(tokens[4])
                                *Block.blocksize));
            }



        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }



    public Block[][] getBlocks(){
        return blocks;
    }

    public ArrayList<MovingBlock> getMovingBlocks(){
        return movingBlocks;
    }

}
