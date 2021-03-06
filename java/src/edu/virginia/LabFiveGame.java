package edu.virginia;

import edu.virginia.engine.display.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabFiveGame extends Game {

    AnimatedSprite mario = new AnimatedSprite("Mario", "Mario.png", new Point(0, 200));
    AnimatedSprite star = new AnimatedSprite("Star", "star1.png", new Point(2040, 200));
    AnimatedSprite brick1 = new AnimatedSprite("Brick", "brick.png", new Point(0, 400));
    AnimatedSprite brick2 = new AnimatedSprite("Brick4", "brick.png", new Point(400, 400));
    AnimatedSprite brick3 = new AnimatedSprite("Brick4", "brick.png", new Point(800, 400));
    AnimatedSprite brick4 = new AnimatedSprite("Brick4", "brick.png", new Point(1200, 400));
    AnimatedSprite ball = new AnimatedSprite("Ball", "ball1.png", new Point(600, 0));
    AnimatedSprite cactus = new AnimatedSprite("Cactus", "cactus1.png", new Point(300, 280));
    AnimatedSprite spark = new AnimatedSprite("Spark", "spark.png", new Point(1000, 1000));

    int score = 0;
    boolean vlast = false;
    boolean collisionlast = false;
    boolean success = false;
    int count = -1;

    private void initFrames() {
        if (mario != null) {
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

    private void initAnimations() {
        Animation a = new Animation("jump,", 0, 10);
        mario.setAnimations(a);
    }

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     */
    public LabFiveGame() {
        super("Lab Five Game", 2500, 1500);
        mario.setPhysics(true);
        star.setScaleX(getScaleX() * 0.125);
        star.setScaleY(getScaleY() * 0.125);
        ball.setPhysics(true);
        ball.setScaleX(getScaleX() * 0.125);
        ball.setScaleY(getScaleY() * 0.125);
        cactus.setScaleX(getScaleX() * 0.125);
        cactus.setScaleY(getScaleY() * 0.125);
        spark.setScaleX(getScaleX() * 0.2);
        spark.setScaleY(getScaleY() * 0.2);
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     */
    @Override
    public void update(ArrayList<Integer> pressedKeys) {

        super.update(pressedKeys);
        /* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if (mario != null) mario.update(pressedKeys);

        Point holdPosition;
        ArrayList<DisplayObject> gameObjects = new ArrayList<DisplayObject>();
        gameObjects.add(mario);
        gameObjects.add(brick1);
        gameObjects.add(brick2);
        gameObjects.add(brick3);
        gameObjects.add(brick4);
        gameObjects.add(ball);
        gameObjects.add(cactus);
        //gameObjects.add(spark);

        mario.updateHitbox();
        star.updateHitbox();
        spark.setHitbox(null);
        for (DisplayObject gameObject : gameObjects) {
            gameObject.updateHitbox();
        }


        if (success) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
            }
            System.exit(0);
        }

        mario.doGravity(gameObjects);
        for (DisplayObject gameObject : gameObjects) {
            gameObject.doGravity(gameObjects);
        }

        /* Add key press event to update visibility */
        if (pressedKeys.size() == 0 && vlast) {
            pressedKeys.add(KeyEvent.KEY_PRESSED);
        }

        if (pressedKeys.contains(KeyEvent.VK_T)) {
            mario.setAccelerationXN(10.0f);
            mario.setAccelerationXP(10.0f);
            mario.setAccelerationYN(10.0f);
            mario.setAccelerationYP(10.0f);

        }

        ArrayList<DisplayObject> collisions = new ArrayList<DisplayObject>();

        /* Iterate through pressed keys arraylist */
        for (int counter = 0; counter < pressedKeys.size(); counter++) {
            /* Logic for Up Arrowkey Press */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_UP)) {
                collisions.addAll(mario.tryMove(0, Math.round(-15 * mario.getAccelerationYN()), gameObjects));
            }
            /* Logic for Down Arrowkey Press */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_DOWN)) {
                collisions.addAll(mario.tryMove(0, Math.round(5 * mario.getAccelerationYP()), gameObjects));
            }
            /* Logic for Left Arrowkey Press */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_LEFT)) {
                collisions.addAll(mario.tryMove(Math.round(-5 * mario.getAccelerationXN()), 0, gameObjects));
            }
            /* Logic for Right Arrowkey Press */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_RIGHT)) {
                collisions.addAll(mario.tryMove(Math.round(5 * mario.getAccelerationXP()), 0, gameObjects));
            }

            /* Key events which alter pivot point */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_I)) {
                mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y - 1));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_K)) {
                mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y + 1));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_J)) {
                mario.setPivotPoint(new Point(mario.getPivotPoint().x - 1, mario.getPivotPoint().y));
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_L)) {
                mario.setPivotPoint(new Point(mario.getPivotPoint().x + 1, mario.getPivotPoint().y));
                /* Key events which alter rotation */
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_Q)) {
                mario.setRotation(mario.getRotation() - 5);
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_W)) {
                mario.setRotation(mario.getRotation() + 5);
                /* Key events which alter visibility */
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_V)) {
                vlast = true;
                /* Key events which alter transparency */
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_Z)) {
                if (mario.getAlpha() < 1.0f) {
                    mario.setOldAlpha(mario.getAlpha());
                    mario.setAlpha(mario.getAlpha() + 0.1f);
                }
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_X)) {
                if (mario.getAlpha() - 0.1f >= 0.0f) {
                    mario.setOldAlpha(mario.getAlpha());
                    mario.setAlpha(mario.getAlpha() - 0.1f);
                }
                /* Key events which alter scaling */
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_A)) {
                mario.setScaleX(mario.getScaleX() + 0.05);
                mario.setScaleY(mario.getScaleY() + 0.05);
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_S)) {
                if (mario.getScaleX() > 0.01) {
                    mario.setScaleX(mario.getScaleX() - 0.05);
                }
                if (mario.getScaleY() > 0.01) {
                    mario.setScaleY(mario.getScaleY() - 0.05);
                }
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_PLUS)) {
                mario.setAnimationSpeed(mario.getAnimationSpeed() + 1);
            } else if (pressedKeys.get(counter).equals(KeyEvent.VK_MINUS)) {
                mario.setAnimationSpeed(mario.getAnimationSpeed() - 1);
            }

            /* Logic for Space Key Press */
            else if (pressedKeys.get(counter).equals(KeyEvent.VK_SPACE)) {
                //mario.setPlaying(true);
                //mario.animate("jump");
                mario.jump(true);
                mario.setCount(mario.getCount() + 1);
                SoundManager s = new SoundManager("jump");
                for (int i = 0; i < 2; i++)
                    pressedKeys.add(1);
            } else if (pressedKeys.get(counter).equals(1)) {
                //mario.jump(false);
                if (mario.getCount() >= 500) {
                    mario.setCount(0);
                    //mario.jump(false);
                    mario.setImage(mario.readImage("Mario.png"));
                } else
                    mario.setCount(mario.getCount() + 1);
            }
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

        /* Manage Acceleration */
        if (pressedKeys.contains(KeyEvent.VK_UP)) {
            if (mario.getAccelerationYN() <= 2) {
                mario.setAccelerationYN(mario.getAccelerationYN() + 0.05f);
            }
        } else {
            mario.setAccelerationYN(1.0f);
        }
        if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
            if (mario.getAccelerationYP() <= 5) {
                mario.setAccelerationYP(mario.getAccelerationYP() + 0.05f);
            }
        } else {
            mario.setAccelerationYP(1.0f);
        }
        if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
            if (mario.getAccelerationXN() <= 5) {
                mario.setAccelerationXN(mario.getAccelerationXN() + 0.05f);
            }
        } else {
            mario.setAccelerationXN(1.0f);
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            if (mario.getAccelerationXP() <= 5) {
                mario.setAccelerationXP(mario.getAccelerationXP() + 0.05f);
            }
        } else {
            mario.setAccelerationXP(1.0f);
        }

        if (!collisions.isEmpty() && !collisionlast) {
            for (DisplayObject collision : collisions) {
                if (!collision.getPhysics()) {
                    score = score - 10;
                    SoundManager s = new SoundManager("bump");
                }
            }
            collisionlast = true;
        } else if (!collisions.isEmpty() && collisionlast) {
            collisionlast = true;
        } else {
            collisionlast = false;
        }

        if (mario.collidesWith(star)) {
            score = score + 10000;
            SoundManager s = new SoundManager("win");
            success = true;
        }
        /*

        if (collisions.contains(ball)) {
            ball.tryMove(5, 0, gameObjects);
        }*/
        if (count >= 0)
            count++;

        if (count > 4)
            count = -1;

        if (!collisionlast && count < 0) {
            spark.setVisible(false);
            spark.setPosition(new Point(1000, 1000));
        }


        for (DisplayObject collisionObject : collisions) {
            if (collisionObject.getPhysics()) {
                if (mario.getPosition().x > collisionObject.getPosition().x) {
                    collisionObject.tryMove(Math.round(-5 * mario.getAccelerationXN()), 0, gameObjects);
                } else {
                    collisionObject.tryMove(Math.round(5 * mario.getAccelerationXP()), 0, gameObjects);
                }
                if (mario.getPosition().y > collisionObject.getPosition().y) {
                    collisionObject.tryMove(0, Math.round(-5 * mario.getAccelerationYN()), gameObjects);
                } else {
                    collisionObject.tryMove(0, Math.round(5 * mario.getAccelerationYP()), gameObjects);
                }
            } else {
                spark.setVisible(true);
                spark.setHitbox(null);
                spark.setPosition(new Point((mario.getPosition().x + collisionObject.getPosition().x) / 2, (mario.getPosition().y + collisionObject.getPosition().y) / 2));
                count = 0;
            }
        }
    }


    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     */
    @Override
    public void draw(Graphics g) {
        super.draw(g);

        Graphics2D g2d = (Graphics2D) g;

        if (mario != null) mario.draw(g);
        //g2d.draw(mario.getHitbox()); used for debugging

        ArrayList<DisplayObject> gameObjects = new ArrayList<DisplayObject>();
        gameObjects.add(brick1);
        gameObjects.add(brick2);
        gameObjects.add(brick3);
        gameObjects.add(brick4);
        gameObjects.add(ball);
        gameObjects.add(cactus);
        gameObjects.add(spark);

        mario.updateHitbox();
        star.updateHitbox();
        for (DisplayObject gameObject : gameObjects) {
            if (gameObject != null) gameObject.draw(g);
            //if(gameObject.getHitbox() != null) g2d.draw(gameObject.getHitbox()); used for debugging
        }

        if (star != null) star.draw(g);
        //g2d.draw(star.getHitbox()); used for debugging

        if (mario.collidesWith(star)) {
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 100));
            g2d.drawString("# YOU WIN #", 810, 100);
            g2d.drawString("Score = " + Integer.toString(score), 810, 1200);
        } else {
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 50));
            g2d.drawString("Score = " + Integer.toString(score), 10, 1200);
        }
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     */
    public static void main(String[] args) {
        LabFiveGame game = new LabFiveGame();
        game.initFrames();
        game.initAnimations();
        game.start();
        SoundManager s1 = new SoundManager("readygo");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted.");
        }
        SoundManager s2 = new SoundManager("marioMusic");
    }
}