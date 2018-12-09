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
public class FullGame extends Game{

    int score = 0;
    int level = 1;
    boolean vlast = false;
    boolean collisionlast = false;
    boolean success = false;

    ArrayList<ArrayList<DisplayObject>> Levels = new ArrayList<ArrayList<DisplayObject>>();

    ArrayList<DisplayObject> gameObjectsL1 = new ArrayList<DisplayObject>();
    ArrayList<ArrayList<Dial>> minigamesL1 = new ArrayList<ArrayList<Dial>>();

    ArrayList<DisplayObject> gameObjectsL2 = new ArrayList<DisplayObject>();
    ArrayList<ArrayList<Dial>> minigamesL2 = new ArrayList<ArrayList<Dial>>();

    ArrayList<DisplayObject> gameObjectsL3 = new ArrayList<DisplayObject>();
    ArrayList<ArrayList<Dial>> minigamesL3 = new ArrayList<ArrayList<Dial>>();

    ArrayList<DisplayObject> inventory = new ArrayList<DisplayObject>();

    public void setGameObjectsL1(ArrayList<DisplayObject> go) {this.gameObjectsL1 = go;}
    public ArrayList<DisplayObject> getGameObjectsL1() {return gameObjectsL1;}
    public void setMinigamesL1(ArrayList<ArrayList<Dial>> mg) {this.minigamesL1 = mg;}
    public ArrayList<ArrayList<Dial>> getMinigamesL1() {return minigamesL1;}

    public void setGameObjectsL2(ArrayList<DisplayObject> go) {this.gameObjectsL2 = go;}
    public ArrayList<DisplayObject> getGameObjectsL2() {return gameObjectsL2;}
    public void setMinigamesL2(ArrayList<ArrayList<Dial>> mg) {this.minigamesL2 = mg;}
    public ArrayList<ArrayList<Dial>> getMinigamesL2() {return minigamesL2;}

    public void setGameObjectsL3(ArrayList<DisplayObject> go) {this.gameObjectsL3 = go;}
    public ArrayList<DisplayObject> getGameObjectsL3() {return gameObjectsL3;}
    public void setMinigamesL3(ArrayList<ArrayList<Dial>> mg) {this.minigamesL3 = mg;}
    public ArrayList<ArrayList<Dial>> getMinigamesL3() {return minigamesL3;}

    /* Interactable Objects */

    AnimatedSprite mario = new AnimatedSprite("Mario", "Mario.png", new Point(1000,1000));

    AnimatedSprite stairs1 = new AnimatedSprite("Stairs1", "stairs1.png", new Point(745,-540));

    AnimatedSprite key1 = new AnimatedSprite("Key1", "key1.png", new Point(-600,1200));

    AnimatedSprite puzzle1L2Entry = new AnimatedSprite("L2Minigame1", "puzzle7.png", new Point(820,0));

    private void initFrames()
    {
        if (mario != null) {
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
    public FullGame() {
        super("Lab Five Game", 2000, 2000);

        /* ##### Level 1 ###### */

        stairs1.setScaleX(getScaleX());
        stairs1.setScaleY(getScaleY() * 0.5);

        key1.setScaleX(getScaleX() * 0.125);
        key1.setScaleY(getScaleY() * 0.125);

        this.getGameObjectsL1().add(mario);
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(0, 400)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(-625, -225)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(-625, 400)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(0, -225)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(0, 1600)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(0, 2225)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(-625, 1600)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(-625, 2225)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(1500, 400)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(2125, 400)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(1500, -225)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(2125, -225)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(1500, 1600)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(1500, 2225)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(2125, 1600)));
        this.getGameObjectsL1().add(new AnimatedSprite("Brick1", "brick.png", new Point(2125, 2225)));

        gameObjectsL1.add(stairs1);
        gameObjectsL1.add(key1);

        /* ##### Level 2 ##### */

        this.getGameObjectsL2().add(mario);
        this.getGameObjectsL2().add(new AnimatedSprite("Brick1", "brick.png", new Point(0, 550)));
        this.getGameObjectsL2().add(new AnimatedSprite("Brick1", "brick.png", new Point(1500, 550)));
        this.getGameObjectsL2().add(new AnimatedSprite("Stairs1", "stairs1.png", new Point(745,1150)));
        this.getGameObjectsL2().add(new AnimatedSprite("L2Minigame1", "puzzle7.png", new Point(820,0)));

        //this.getGameObjectsL2().add(new Dial("bear1.png", "bear1.png", "butterfly1.png", "crab1.png", new Point(0, 0)));
        this.getGameObjectsL2().add(new AnimatedSprite("bear", "bear1.png", new Point(0, 0)));

        /* ##### Level 3 ##### */
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

        ArrayList<DisplayObject> gameObjects = new ArrayList<>();

        if (level == 1) {
            gameObjects = this.getGameObjectsL1();
        } else if (level == 2) {
            gameObjects = this.getGameObjectsL2();
        } else if (level == 3) {
            gameObjects = this.getGameObjectsL3();
        } else {
            exitGame();
        }

        for (DisplayObject gameObject : gameObjects) {
            gameObject.updateHitbox();
        }

        if (success) {
            try {
                Thread.sleep(4000);
            } catch(InterruptedException e) {
                System.out.println("Interrupted.");
            }
            System.exit(0);
        }

        /* Add key press event to update visibility */
        if (pressedKeys.size() == 0 && vlast) { pressedKeys.add(KeyEvent.KEY_PRESSED); }

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
                collisions.addAll(mario.tryMove(0, Math.round(-5 * mario.getAccelerationYN()), gameObjects));
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

        /* Manage Acceleration */
        if (pressedKeys.contains(KeyEvent.VK_UP)) {
            if (mario.getAccelerationYN() <= 5) {mario.setAccelerationYN(mario.getAccelerationYN() + 0.05f);}
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

        /*
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
        */

        if (collisions.contains(key1)) {
            inventory.add(key1);
            this.getGameObjectsL1().remove(key1);
        }

        if (inventory.contains(key1) && stairs1.getPosition().y > 650) {
            SoundManager s = new SoundManager("win");
            level = 2;
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
                ;
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

        Graphics2D g2d = (Graphics2D) g;


        if (level == 1) {
            for (DisplayObject gameObject : this.getGameObjectsL1()) {
                if(gameObject != null) gameObject.draw(g);
            }
        } else if (level == 2) {
            for (DisplayObject gameObject : this.getGameObjectsL2()) {
                if(gameObject != null) gameObject.draw(g);
            }
        } else if (level == 3) {
            for (DisplayObject gameObject : this.getGameObjectsL3()) {
                if(gameObject != null) gameObject.draw(g);
            }
        } else {
            ;
        }

        /*
        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g2d.drawString("Position = " + Integer.toString(mario.getPosition().x) + " " + Integer.toString(mario.getPosition().y), 10, 1200);
        */

        /*
        if (false) {
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 100));
            g2d.drawString("# YOU WIN #", 810, 100);
            g2d.drawString("Score = " + Integer.toString(score), 810, 1200);
        } else {
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 50));
            g2d.drawString("Position = " + Integer.toString(mario.getPosition().x) + " " + Integer.toString(mario.getPosition().y), 10, 1200);
        }*/
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        FullGame game = new FullGame();
        game.initFrames();
        game.initAnimations();
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
