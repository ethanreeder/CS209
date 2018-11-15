package edu.virginia;

import edu.virginia.engine.display.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabFiveGame extends Game{

    AnimatedSprite mario = new AnimatedSprite("Mario", "Mario.png", new Point(0,0));
    AnimatedSprite star = new AnimatedSprite("Star", "star1.png", new Point(2040,40));

    AnimatedSprite brick1 = new AnimatedSprite("Brick", "brick.png", new Point(0, 400));

    int score = 0;
    boolean vlast = false;
    boolean collisionlast = false;
    boolean success = false;
    float gravity = 0.0f;

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
    public LabFiveGame() {
        super("Lab Five Game", 2500, 2000);
        star.setScaleX(getScaleX() * 0.125);
        star.setScaleY(getScaleY() * 0.125);
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

        Point holdPosition;

        mario.updateHitbox();
        star.updateHitbox();
        brick1.updateHitbox();
        /*
        for (int counter = 0; counter < this.getGameObjects().size(); counter++) {
            DisplayObject curr = this.getGameObjects().get(counter);
            if (curr != null) {
                curr.updateHitbox();
            }
        }*/

        if (success) {
            try {
                Thread.sleep(4000);
            } catch(InterruptedException e) {
                System.out.println("Interrupted.");
            }
            System.exit(0);
        }

        holdPosition = mario.getPosition();
        mario.setPosition(new Point(mario.getPosition().x, Math.round(mario.getPosition().y+gravity)));
        mario.updateHitbox();
        if (mario.collidesWith(brick1)) { // Collides with any
            mario.setPosition(holdPosition);
            mario.updateHitbox();
            gravity = 0.0f;
        } else {
            gravity += 0.5f;
        }

        /* Add key press event to update visibility */
        if (pressedKeys.size() == 0 && vlast) { pressedKeys.add(KeyEvent.KEY_PRESSED); }

        for (int counter = 0; counter < pressedKeys.size(); counter++) {

            /* Logic for Up Arrowkey Press */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_UP)) {
                holdPosition = mario.getPosition();
                if (mario.collidesWith(brick1)) { // Collides with any
                    mario.setPosition(new Point(mario.getPosition().x, Math.round(mario.getPosition().y-(5 * mario.getAccelerationYN()))));
                    mario.updateHitbox();
                    if (mario.collidesWith(brick1)) { // Collides with any
                        mario.setPosition(holdPosition);
                        mario.updateHitbox();
                    } else {
                        ;
                    }
                } else {
                    mario.setPosition(new Point(mario.getPosition().x, Math.round(mario.getPosition().y-(5 * mario.getAccelerationYN()))));
                    mario.updateHitbox();
                    if (mario.collidesWith(brick1)) { // Collides with any
                        mario.setPosition(new Point(holdPosition));
                        mario.updateHitbox();
                        mario.setAccelerationYN(1.0f);
                        collisionlast = true;
                    } else {
                        ;
                    }
                }
            } else {
                ;
            }
            /* Logic for Down Arrowkey Press */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_DOWN)) {
                holdPosition = mario.getPosition();
                if (mario.collidesWith(brick1)) { // Collides with any
                    mario.setPosition(new Point(mario.getPosition().x, Math.round(mario.getPosition().y+(5 * mario.getAccelerationYP()))));
                    mario.updateHitbox();
                    if (mario.collidesWith(brick1)) { // Collides with any
                        mario.setPosition(holdPosition);
                        mario.updateHitbox();
                    } else {
                        ;
                    }
                } else {
                    mario.setPosition(new Point(mario.getPosition().x, Math.round(mario.getPosition().y+(5 * mario.getAccelerationYP()))));
                    mario.updateHitbox();
                    if (mario.collidesWith(brick1)) { // Collides with any
                        mario.setPosition(new Point(holdPosition));
                        mario.updateHitbox();
                        mario.setAccelerationYP(1.0f);
                        collisionlast = true;
                    } else {
                        ;
                    }
                }
            } else {
                mario.setAccelerationYP(1.0f);
            }
            /* Logic for Left Arrowkey Press */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_LEFT)) {
                holdPosition = mario.getPosition();
                if (mario.collidesWith(brick1)) { // Collides with any
                    mario.setPosition(new Point(Math.round(mario.getPosition().x-(5 * mario.getAccelerationXN())), mario.getPosition().y));
                    mario.updateHitbox();
                    if (mario.collidesWith(brick1)) { // Collides with any
                        mario.setPosition(holdPosition);
                        mario.updateHitbox();
                    } else {
                        ;
                    }
                } else {
                    mario.setPosition(new Point(Math.round(mario.getPosition().x-(5 * mario.getAccelerationXN())), mario.getPosition().y));
                    mario.updateHitbox();
                    if (mario.collidesWith(brick1)) { // Collides with any
                        mario.setPosition(new Point(holdPosition));
                        mario.updateHitbox();
                        mario.setAccelerationXN(1.0f);
                        collisionlast = true;
                    } else {
                        ;
                    }
                }
            } else {
                mario.setAccelerationXN(1.0f);
            }
            /* Logic for Right Arrowkey Press */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_RIGHT)) {
                holdPosition = mario.getPosition();
                if (mario.collidesWith(brick1)) { // Collides with any
                    mario.setPosition(new Point(Math.round(mario.getPosition().x+(5 * mario.getAccelerationXP())), mario.getPosition().y));
                    mario.updateHitbox();
                    if (mario.collidesWith(brick1)) { // Collides with any
                        mario.setPosition(holdPosition);
                    } else {
                        ;
                    }
                } else {
                    mario.setPosition(new Point(Math.round(mario.getPosition().x+(5 * mario.getAccelerationXP())), mario.getPosition().y));
                    mario.updateHitbox();
                    if (mario.collidesWith(brick1)) { // Collides with any
                        mario.setPosition(holdPosition);
                        mario.updateHitbox();
                        mario.setAccelerationXP(1.0f);
                        collisionlast = true;
                    } else {
                        ;
                    }
                }
            } else {
                mario.setAccelerationXP(1.0f);
            }

            /* Key events which alter pivot point */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_I)) {
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
            else if(pressedKeys.get(counter).equals(KeyEvent.VK_PLUS)) {
                mario.setAnimationSpeed(mario.getAnimationSpeed()+1);
            }
            else if(pressedKeys.get(counter).equals(KeyEvent.VK_MINUS)) {
                mario.setAnimationSpeed(mario.getAnimationSpeed()-1);
            }

            /* Logic for Space Key Press */
            else if (pressedKeys.get(counter).equals(KeyEvent.VK_SPACE)){
                //mario.setPlaying(true);
                //mario.animate("jump");
                mario.jump(true);
                mario.setCount(mario.getCount()+1);
                SoundManager s = new SoundManager("jump");

                for(int i = 0; i < 2; i++)
                    pressedKeys.add(1);
            } else if(pressedKeys.get(counter).equals(1)) {
                //mario.jump(false);
                if(mario.getCount() >= 500) {
                    mario.setCount(0);
                    //mario.jump(false);
                    mario.setImage(mario.readImage("Mario.png"));
                }
                else
                    mario.setCount(mario.getCount()+1);
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


        if (pressedKeys.contains(KeyEvent.VK_UP)) {
            if (mario.getAccelerationYN() <= 2) {mario.setAccelerationYN(mario.getAccelerationYN() + 0.05f);}
        } else {
            mario.setAccelerationYN(1.0f);
        }
        if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
            if (mario.getAccelerationYP() <= 5) {mario.setAccelerationYP(mario.getAccelerationYP() + 0.05f);}
        } else {
            mario.setAccelerationYP(1.0f);
        }
        if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
            if (mario.getAccelerationXN() <= 5) {mario.setAccelerationXN(mario.getAccelerationXN() + 0.05f);}
        } else {
            mario.setAccelerationXN(1.0f);
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            if (mario.getAccelerationXP() <= 5) {mario.setAccelerationXP(mario.getAccelerationXP() + 0.05f);}
        } else {
            mario.setAccelerationXP(1.0f);
        }

        if (collisionlast) {
            SoundManager s = new SoundManager("bump");
            score = score - 10;
            collisionlast = false;
        } else {
            collisionlast = false;
        }

        if (mario.collidesWith(star)) {
            score = score + 10000;
            SoundManager s = new SoundManager("win");
            success = true;
        }

        mario.updateHitbox();
        star.updateHitbox();
        brick1.updateHitbox();
        /*
        for (int counter = 0; counter < this.getGameObjects().size(); counter++) {
            DisplayObject curr = this.getGameObjects().get(counter);
            if (curr != null) {
                curr.updateHitbox();
            }
        }*/
    }



    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        Graphics2D g2d = (Graphics2D) g;

        if(mario != null) mario.draw(g);
        g2d.draw(mario.getHitbox());

        /*
        for (int counter = 0; counter < this.getGameObjects().size(); counter++) {
            DisplayObject curr = this.getGameObjects().get(counter);
            if (curr != null) {
                curr.draw(g);
                g2d.draw(curr.getHitbox());
            }
        }*/

        if(star != null) star.draw(g);
        g2d.draw(star.getHitbox());
        if(brick1 != null) brick1.draw(g);
        g2d.draw(brick1.getHitbox());

        if (mario.collidesWith(star)) {
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 100));
            g2d.drawString("# YOU WIN #", 810, 900);
            g2d.drawString("Score = " + Integer.toString(score), 810, 985);
        } else {
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 50));
            g2d.drawString("Score = " + Integer.toString(score), 10, 1800);
        }
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        LabFiveGame game = new LabFiveGame();
        game.initFrames();
        game.initAnimations();

        /*
        AnimatedSprite brick2 = new AnimatedSprite("Brick4", "brick.png", new Point(0,400));
        game.addGameObject(brick2);
        AnimatedSprite brick3 = new AnimatedSprite("Brick4", "brick.png", new Point(400,400));
        game.addGameObject(brick3);
        AnimatedSprite brick4 = new AnimatedSprite("Brick4", "brick.png", new Point(800,400));
        game.addGameObject(brick4);
        */
        game.start();
        SoundManager s1 = new SoundManager("readygo");
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            System.out.println("Interrupted.");
        }
        SoundManager s2 = new SoundManager("marioMusic");
    }
}
