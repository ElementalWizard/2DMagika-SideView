package main;

import Game.GameStates.GameStateManager;
import Resources.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Elemental on 12/10/2016.
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {
    public static final long serialVersionUID = 1L;
    private Thread thread;
    private boolean isRunning = false;
    private int FPS = 60;
    private Long targetTime = (long) (1000 / FPS);
    private GameStateManager gsm;
    public static final int WIDTH=800;
    public static final int HEIGHT=500;

    public GamePanel(){

        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        addKeyListener(this);
        setFocusable(true);
        new Images();
        start();

    }
    public void start(){
        isRunning=true;
        thread=new Thread(this);
        thread.start();//calls run method
    }

    @Override
    public void run() {
        //actual Gameloop
        long start,elapsed,wait;
        gsm = new GameStateManager();


        while(isRunning) {
            start=System.nanoTime();

            tick();
            repaint();

            elapsed =System.nanoTime()-start;
            wait = targetTime-elapsed/1000000;

            if(wait <= 0){
                wait=5;
            }
            try{
                Thread.sleep(wait);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public void tick(){
        gsm.tick();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.clearRect(0,0,this.getWidth(),this.getHeight());
        gsm.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gsm.KeyReleased(e.getKeyCode());
    }

}
