package edu.virginia;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Animation;
import edu.virginia.engine.display.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabTwoGame extends Game{

    /* Create a sprite object for our game. We'll use mario */
    AnimatedSprite mario = new AnimatedSprite("Mario", "Mario.png", new Point(0,0));
    boolean vlast = false;


    private void initFrames()
    {
        if(mario != null)
        {
            /*
            for(int i = 1; i < 12; i++)
                mario.addFrame("jump"+i+".png");
            */
            mario.addFrame("jump1.png");
            mario.addFrame("jump2.png");
            mario.addFrame("jump3.png");
            mario.addFrame("jump4.png");
            mario.addFrame("jump5.png");
            mario.addFrame("jump6.png");
            mario.addFrame("jump7.png");
            mario.addFrame("jump8.png");
            mario.addFrame("jump9.png");
            mario.addFrame("jump10.png");
            mario.addFrame("jump11.png");
        }
    }

    private void initAnimations()
    {
        Animation a = new Animation("jump,", 0, 10);
        mario.setAnimations(a);
    }
    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public LabTwoGame() {
        super("Lab Two Game", 500, 300);
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

        /* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if(mario != null) mario.update(pressedKeys);

        /*
        if (mario.getCount() < 30) {
            mario.setCount(mario.getCount() + 1);
        }
        */

        /*
        if (!mario.getPaused()) {
            if (mario.getFrameCount() < mario.getAnimationSpeed()) {
                mario.setFrameCount(mario.getFrameCount() + 1);
            } else if (mario.getFrameCount() > mario.getAnimationSpeed()) {
                mario.setFrameCount(0);
            }
        }
        */

        /* Add key press event to update visibility */
        if (pressedKeys.size() == 0 && vlast) { pressedKeys.add(KeyEvent.KEY_PRESSED); }

        for (int counter = 0; counter < pressedKeys.size(); counter++) {
            /* Key events which alter position */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_UP)) {
                mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y-5));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_DOWN)) {
                mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y+5));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_LEFT)) {
                mario.setPosition(new Point(mario.getPosition().x-5, mario.getPosition().y));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_RIGHT)) {
                mario.setPosition(new Point(mario.getPosition().x+5, mario.getPosition().y));
                /* Key events which alter pivot point */
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_I)) {
                mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y-1));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_K)) {
                mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y+1));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_J)) {
                mario.setPivotPoint(new Point(mario.getPivotPoint().x-1, mario.getPivotPoint().y));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_L)) {
                mario.setPivotPoint(new Point(mario.getPivotPoint().x+1, mario.getPivotPoint().y));
                /* Key events which alter rotation */
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_Q)) {
                mario.setRotation(mario.getRotation()-5);
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_W)) {
                mario.setRotation(mario.getRotation()+5);
                /* Key events which alter visibility */
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_V)) {
                vlast = true;
                /* Key events which alter transparency */
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_Z)) {
                if(mario.getAlpha() < 1.0f) {
                    mario.setOldAlpha(mario.getAlpha());
                    mario.setAlpha(mario.getAlpha()+0.1f);
                }
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_X)) {
                if(mario.getAlpha() - 0.1f >= 0.0f) {
                    mario.setOldAlpha(mario.getAlpha());
                    mario.setAlpha(mario.getAlpha() - 0.1f);
                }
                /* Key events which alter scaling */
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_A)) {
                mario.setScaleX(mario.getScaleX()+0.05);
                mario.setScaleY(mario.getScaleY()+0.05);
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_S)) {
                if (mario.getScaleX() > 0.01) {
                    mario.setScaleX(mario.getScaleX()-0.05);
                }
                if (mario.getScaleY() > 0.01) {
                    mario.setScaleY(mario.getScaleY()-0.05);
                }
            }
            else if(pressedKeys.get(counter).equals(KeyEvent.VK_PLUS))
            {
                mario.setAnimationSpeed(mario.getAnimationSpeed()+1);
            }
            else if(pressedKeys.get(counter).equals(KeyEvent.VK_MINUS))
            {
                mario.setAnimationSpeed(mario.getAnimationSpeed()-1);
            }
            else if (pressedKeys.get(counter).equals(KeyEvent.VK_SPACE)){
                //mario.setPlaying(true);
                //mario.animate("jump");
                mario.jump(true);
                mario.setCount(mario.getCount()+1);

                for(int i = 0; i < 2; i++)
                    pressedKeys.add(1);


            } else if(pressedKeys.get(counter).equals(1))
            {
                //mario.jump(false);

                if(mario.getCount() >= 500) {
                    mario.setCount(0);
                    //mario.jump(false);
                    mario.setImage(mario.readImage("Mario.png"));
                }
                else
                mario.setCount(mario.getCount()+1);
            }



            else {
                ;
            }
            /* Check if v was released in order to toggle visibility */
            if (!pressedKeys.contains(KeyEvent.VK_V) && vlast) {
                if (mario.getVisible()) {
                    mario.setVisible(false);
                } else {
                    mario.setVisible(true);
                }
                vlast = false;
            }
        }
    }



    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        /* Same, just check for null in case a frame gets thrown in before Mario is initialized */
        if(mario != null) mario.draw(g);
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        LabTwoGame game = new LabTwoGame();
        game.initFrames();
        game.initAnimations();
        game.start();

    }
}
