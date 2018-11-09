package edu.virginia;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabThreeGame extends Game{

    /* Create a sprite object for our game. We'll use sun */
    Sprite sun = new Sprite("Sun", "sun3.png");

    boolean vlast = false;

    int clockwise = 1;
    int scalar = 1;

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public LabThreeGame() {
        super("Lab Three Game", 2000, 1000);
        sun.setScaleX(.25);
        sun.setScaleY(.25);
        sun.setPosition(new Point(600,350));
        sun.setPivotPoint(sun.getPosition());
        sun.addChild(new Sprite("Earth", "earth2.png"));
        sun.addChild(new Sprite("Neptune", "neptune2.png"));


        DisplayObjectContainer earth = (DisplayObjectContainer) sun.getChildren().get(0);
        earth.setScaleX(.5);
        earth.setScaleY(.5);
        earth.setPosition(new Point(1500,0));
        earth.addChild(new Sprite("Moon,", "moon.png"));
        earth.setPivotPoint(earth.globalToLocal(earth.getPosition()));


        DisplayObjectContainer moon = (DisplayObjectContainer) earth.getChildren().get(0);
        moon.setScaleX(.75);
        moon.setScaleY(.75);
        moon.setPosition(new Point(1000, 0));
        moon.setPivotPoint(earth.globalToLocal(earth.getPosition()));

        DisplayObjectContainer neptune = (DisplayObjectContainer) sun.getChildren().get(1);
        neptune.setScaleX(.5);
        neptune.setScaleY(.5);
        neptune.setPosition(new Point(2000,0));
        neptune.setPivotPoint(sun.globalToLocal(sun.getPosition()));

    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

        /* Make sure sun is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if(sun != null) sun.update(pressedKeys);

        /*
        if (sun.getCount() < 30) {
            sun.setCount(sun.getCount() + 1);
        }
        */

        /*
        if (!sun.getPaused()) {
            if (sun.getFrameCount() < sun.getAnimationSpeed()) {
                sun.setFrameCount(sun.getFrameCount() + 1);
            } else if (sun.getFrameCount() > sun.getAnimationSpeed()) {
                sun.setFrameCount(0);
            }
        }
        */

        DisplayObjectContainer earth = (DisplayObjectContainer) sun.getChildren().get(0);
        DisplayObjectContainer moon = (DisplayObjectContainer) earth.getChildren().get(0);
        DisplayObjectContainer neptune = (DisplayObjectContainer) sun.getChildren().get(1);


        /* Add key press event to update visibility */
        if (pressedKeys.size() == 0 && vlast) { pressedKeys.add(KeyEvent.KEY_PRESSED); }

        for (int counter = 0; counter < pressedKeys.size(); counter++) {
            /* Key events which alter position */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_UP)) {
                sun.setPosition(new Point(sun.getPosition().x, sun.getPosition().y+5));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_DOWN)) {
                sun.setPosition(new Point(sun.getPosition().x, sun.getPosition().y-5));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_LEFT)) {
                sun.setPosition(new Point(sun.getPosition().x+5, sun.getPosition().y));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_RIGHT)) {
                sun.setPosition(new Point(sun.getPosition().x-5, sun.getPosition().y));
                /* Key events which alter pivot point */
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_I)) {
                sun.setPivotPoint(new Point(sun.getPivotPoint().x, sun.getPivotPoint().y-1));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_K)) {
                sun.setPivotPoint(new Point(sun.getPivotPoint().x, sun.getPivotPoint().y+1));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_J)) {
                sun.setPivotPoint(new Point(sun.getPivotPoint().x-1, sun.getPivotPoint().y));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_L)) {
                sun.setPivotPoint(new Point(sun.getPivotPoint().x+1, sun.getPivotPoint().y));
                /* Key events which alter rotation */
                /* Key events which alter visibility */
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_V)) {
                vlast = true;
                /* Key events which alter transparency */
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_Z)) {
                if(sun.getAlpha() < 1.0f) {
                    sun.setOldAlpha(sun.getAlpha());
                    sun.setAlpha(sun.getAlpha()+0.1f);
                }
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_X)) {
                if(sun.getAlpha() - 0.1f >= 0.0f) {
                    sun.setOldAlpha(sun.getAlpha());
                    sun.setAlpha(sun.getAlpha() - 0.1f);
                }
                /* Key events which alter scaling */
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_Q)) {
                sun.setScaleX(sun.getScaleX()+0.05);
                sun.setScaleY(sun.getScaleY()+0.05);
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_W)) {
                if (sun.getScaleX() > 0.01) {
                    sun.setScaleX(sun.getScaleX()-0.05);
                }
                if (sun.getScaleY() > 0.01) {
                    sun.setScaleY(sun.getScaleY()-0.05);
                }
            }
            else if (pressedKeys.get(counter).equals(KeyEvent.VK_A)){
                clockwise = -1;
            }
            else if (pressedKeys.get(counter).equals(KeyEvent.VK_S)){
                clockwise = 1;
            }
            /* Check if v was released in order to toggle visibility */
            if (!pressedKeys.contains(KeyEvent.VK_V) && vlast) {
                if (sun.getVisible()) {
                    sun.setVisible(false);
                } else {
                    sun.setVisible(true);
                }
                vlast = false;
            }
        }


        earth.setRotation(earth.getRotation() + clockwise* 0.1);

        //earth.setPosition(new Point(earth.getPosition().x+1, earth.getPosition().y+1));
        //sun.setPivotPoint(sun.getPosition());
        //sun.setRotation(sun.getRotation()+.1);

        moon.setRotation(moon.getRotation() + clockwise * 0.2);

        neptune.setRotation(neptune.getRotation()+ clockwise * 0.1);

        //sun.set
        /*
        if(earth.getPosition().x > sun.getPosition().x) {
            if (earth.getPosition().y > sun.getPosition().y)
                earth.setPosition(new Point(earth.getPosition().x + 1, earth.getPosition().y - 1));
            else
                earth.setPosition(new Point(earth.getPosition().x - 1, earth.getPosition().y - 1));
        }
        else{
            if(earth.getPosition().y > sun.getPosition().y)
            earth.setPosition(new Point(earth.getPosition().x + 1, earth.getPosition().y + 1));
        else
            earth.setPosition(new Point(earth.getPosition().x - 1, earth.getPosition().y + 1));
        }
        */
        //if(sun.getChildren() != null) {
            //Point earthPos = sun.getChildren().get(0).getPosition();
            //Point sunPos = new Point(sun.getPosition().x, sun.getPosition().y);

           // if ((earthPos.x >= sunPos.x) && (earthPos.y >= sunPos.y))
            //    earthPos.x += 1;
        //}

    }



    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure sun gets drawn to
     * the screen, we need to make sure to override this method and call sun's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        /* Same, just check for null in case a frame gets thrown in before sun is initialized */
        if(sun != null) sun.draw(g);
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        LabThreeGame game = new LabThreeGame();
        //game.getsun.addChild(new Sprite("Sun2, sun.png"));
        game.start();

    }
}
