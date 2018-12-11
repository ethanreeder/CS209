package edu.virginia;

import edu.virginia.engine.display.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
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

    AnimatedSprite mario = new AnimatedSprite("Brock", "brockD2.png", new Point(425,275));

    AnimatedSprite stairs1 = new AnimatedSprite("Stairs1", "stairs1.png", new Point(75,-250));

    AnimatedSprite key1 = new AnimatedSprite("Key1", "key1.png", new Point(275,500));

    AnimatedSprite puzzle1L2Entry = new AnimatedSprite("L2Minigame1", "puzzle7.png", new Point(820,0));


    AnimatedSprite stairs2 = new AnimatedSprite("Stairs1", "stairs1.png", new Point(5250,0));

    AnimatedSprite key2 = new AnimatedSprite("Key1", "key2.png", new Point(2524,500));



    AnimatedSprite stairs3 = new AnimatedSprite("Stairs1", "door.png", new Point(900,-1050));

    AnimatedSprite key3 = new AnimatedSprite("Key1", "key3.png", new Point(3000,-200));

    AnimatedSprite key4 = new AnimatedSprite("Key1", "key4.png", new Point(1125,500));



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
        super("Lab Five Game", 1000, 800);

        /* ##### Level 1 ###### */
        if (Math.random() > .5){
            mario.setId("Misty");
            mario.setImage(mario.readImage("mistyD2.png"));
        }

        mario.setScaleX(getScaleX()*.5);
        mario.setScaleY(getScaleY()*.5);

        stairs1.setScaleX(getScaleX());
        stairs1.setScaleY(getScaleY() * 0.5);

        key1.setScaleX(getScaleX() * 0.08);
        key1.setScaleY(getScaleY() * 0.08);

        this.getGameObjectsL1().add(mario);

        AnimatedSprite brick = new AnimatedSprite("Brick1", "brick.png", new Point(-300, 25));
        brick.setScaleX(getScaleX() * 0.5);
        //brick.setScaleY(getScaleY() * 0.5);
        this.getGameObjectsL1().add(brick);


        AnimatedSprite brick2 = new AnimatedSprite("Brick2", "brick.png", new Point(850, 25));
        brick2.setScaleX(getScaleX() * 0.5);
        //brick.setScaleY(getScaleY() * 0.5);
        this.getGameObjectsL1().add(brick2);


        AnimatedSprite brick3 = new AnimatedSprite("Brick3", "brick.png", new Point(75, 650));
        //brick3.setScaleX(getScaleX() * 0.5);
        brick3.setScaleY(getScaleY() * 0.5);
        this.getGameObjectsL1().add(brick3);

        /*
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
        */


        gameObjectsL1.add(stairs1);
        gameObjectsL1.add(key1);

        /* ##### Level 2 ##### */

        this.getGameObjectsL2().add(mario);

        AnimatedSprite brick21 = new AnimatedSprite("Brick1", "wall3.png", new Point(150, 600));
        brick21.setScaleX(getScaleX()*2);
        brick21.setScaleY(getScaleY()*.25);
        this.getGameObjectsL2().add(brick21);

        AnimatedSprite brick22 = new AnimatedSprite("Brick1", "wall3.png", new Point(150, -300));
        brick22.setScaleX(getScaleX()*2);
        brick22.setScaleY(getScaleY()*.25);
        this.getGameObjectsL2().add(brick22);

        AnimatedSprite brick23 = new AnimatedSprite("Brick1", "wall3.png", new Point(100, -100));
        brick23.setScaleX(getScaleX()*.1);
        brick23.setScaleY(getScaleY()*.5);
        this.getGameObjectsL2().add(brick23);

        stairs2.setRotation(90);
        this.getGameObjectsL2().add(stairs2);

        key2.setScaleX(getScaleX() * 0.08);
        key2.setScaleY(getScaleY() * 0.08);
        this.getGameObjectsL2().add(key2);


        //this.getGameObjectsL2().add(new AnimatedSprite("Brick1", "brick.png", new Point(1500, 550)));
        //this.getGameObjectsL2().add(new AnimatedSprite("Stairs1", "stairs1.png", new Point(745,1150)));


        //this.getGameObjectsL2().add(new AnimatedSprite("L2Minigame1", "puzzle7.png", new Point(820,0)));

        //this.getGameObjectsL2().add(new Dial("bear1.png", "bear1.png", "butterfly1.png", "crab1.png", new Point(0, 0)));
        //this.getGameObjectsL2().add(new AnimatedSprite("bear", "bear1.png", new Point(0, 0)));

        /* ##### Level 3 ##### */

        AnimatedSprite brick39 = new AnimatedSprite("Brick1", "wall7.png", new Point(1700, -1000));
        brick39.setScaleX(getScaleX()*.05);
        brick39.setScaleY(getScaleY()*.5);
        brick39.setRotation(90);
        this.getGameObjectsL3().add(brick39);


        this.getGameObjectsL3().add(mario);


        key3.setScaleX(getScaleX()*.08);
        key3.setScaleY(getScaleY()*.08);

        key4.setScaleX(getScaleX()*.08);
        key4.setScaleY(getScaleY()*.08);

        this.getGameObjectsL3().add(key3);
        this.getGameObjectsL3().add(key4);


        AnimatedSprite brick31 = new AnimatedSprite("Brick1", "wall6.png", new Point(-100, -700));
        brick31.setScaleX(getScaleX()*.25);
        brick31.setScaleY(getScaleY()*1.75);
        this.getGameObjectsL3().add(brick31);

        AnimatedSprite brick32 = new AnimatedSprite("Brick1", "wall6.png", new Point(700, -100));
        brick32.setScaleX(getScaleX()*.25);
        this.getGameObjectsL3().add(brick32);

        AnimatedSprite brick36 = new AnimatedSprite("Brick1", "wall6.png", new Point(1500, -100));
        brick36.setScaleX(getScaleX()*2);
        this.getGameObjectsL3().add(brick36);

        AnimatedSprite brick37 = new AnimatedSprite("Brick1", "wall5.png", new Point(1500, -1000));
        brick37.setScaleX(getScaleX()*2);
        this.getGameObjectsL3().add(brick37);

        AnimatedSprite brick38 = new AnimatedSprite("Brick1", "wall7.png", new Point(3100, -200));
        brick38.setScaleX(getScaleX()*.05);
        brick38.setScaleY(getScaleY()*.075);
        this.getGameObjectsL3().add(brick38);



        AnimatedSprite brick33 = new AnimatedSprite("Brick1", "wall7.png", new Point(1750, 700));
        brick33.setScaleX(getScaleX()*.05);
        brick33.setScaleY(getScaleY()*2);
        brick33.setRotation(90);
        this.getGameObjectsL3().add(brick33);


        AnimatedSprite brick34 = new AnimatedSprite("Brick1", "wall5.png", new Point(1300, -600));
        brick34.setScaleX(getScaleX()*.25);
        brick34.setScaleY(getScaleY()*2);
        brick34.setRotation(90);
        this.getGameObjectsL3().add(brick34);

        /*
        AnimatedSprite brick35 = new AnimatedSprite("Brick1", "wall5.png", new Point(700, -400));
        brick35.setScaleX(getScaleX()*.25);
        brick35.setRotation(90);
        this.getGameObjectsL3().add(brick35);
        */


        stairs3.setScaleX(getScaleX()*.6);
        stairs3.setScaleY(getScaleY()*.6);

        this.getGameObjectsL3().add(stairs3);


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
                if(mario.getId().equals("Brock"))   mario.setImage(mario.readImage("brockU2.png"));
                else    mario.setImage(mario.readImage("mistyU2.png"));

                collisions.addAll(mario.tryMove(0, Math.round(-5 * mario.getAccelerationYN()), gameObjects));
            }
            /* Logic for Down Arrowkey Press */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_DOWN)) {
                if(mario.getId().equals("Brock"))   mario.setImage(mario.readImage("brockD2.png"));
                else    mario.setImage(mario.readImage("mistyD2.png"));
                collisions.addAll(mario.tryMove(0, Math.round(5 * mario.getAccelerationYP()), gameObjects));
            }
            /* Logic for Left Arrowkey Press */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_LEFT)) {
                if(mario.getId().equals("Brock"))   mario.setImage(mario.readImage("brockL2.png"));
                else    mario.setImage(mario.readImage("mistyL2.png"));
                collisions.addAll(mario.tryMove(Math.round(-5 * mario.getAccelerationXN()), 0, gameObjects));
            }
            /* Logic for Right Arrowkey Press */
            if (pressedKeys.get(counter).equals(KeyEvent.VK_RIGHT)) {
                if(mario.getId().equals("Brock"))   mario.setImage(mario.readImage("brockR2.png"));
                else    mario.setImage(mario.readImage("mistyR2.png"));
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
            /*
            else if (pressedKeys.get(counter).equals(KeyEvent.VK_SPACE)){
                //mario.setPlaying(true);
                //mario.animate("jump");
                mario.jump(true);
                mario.setCount(mario.getCount()+1);
                SoundManager s = new SoundManager("jump");
                for(int i = 0; i < 2; i++)
                    pressedKeys.add(1);
            }
            */
            else if(pressedKeys.get(counter).equals(1)) {
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
            //key1.setPosition(new Point(mario.getPosition().x+100, mario.getPosition().y+100));
            this.getGameObjectsL1().remove(key1);
        }

        if (collisions.contains(key2)) {
            inventory.add(key2);
            this.getGameObjectsL2().remove(key2);
        }

        if (collisions.contains(key3)) {
            inventory.add(key3);
            this.getGameObjectsL3().remove(key3);
        }

        if (collisions.contains(key4)) {
            inventory.add(key4);
            this.getGameObjectsL3().remove(key4);
        }

        if (inventory.contains(key1) && collisions.contains(stairs1)) {
            SoundManager s = new SoundManager("win");
            level = 2;
        }

        if (inventory.contains(key2) && collisions.contains(stairs2)) {
            SoundManager s = new SoundManager("win");
            level = 3;
        }

        if (inventory.contains(key3) && inventory.contains(key4) && collisions.contains(stairs3)) {
            SoundManager s = new SoundManager("win");
            level = 4;
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

        /*
        for(DisplayObject key : inventory){
            key.setPosition(new Point(mario.getPosition().x+100, mario.getPosition().y - 100));
            key.draw(g);
        }
        */




        if (level == 1) {
            for (DisplayObject gameObject : this.getGameObjectsL1()) {
                if(gameObject != null) gameObject.draw(g);
            }

            //if(inventory.contains(key1) && mario.collidesWith(stairs1)){
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Level 1: The Attic #", 32, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Level 1: The Attic #", 30, 30);


            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Inventory: #", 700, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Inventory: #", 702, 30);


            if(inventory.contains(key1)){
                key1.setPosition(new Point(800, 75));
                key1.draw(g);
            }

            //}
        } else if (level == 2) {
            for (DisplayObject gameObject : this.getGameObjectsL2()) {
                if(gameObject != null) gameObject.draw(g);
            }

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Level N: Ground Floor #", 32, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Level N: Ground Floor #", 30, 30);


            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Inventory: #", 700, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Inventory: #", 702, 30);

            key1.setPosition(new Point(800, 75));
            key1.draw(g);

            if(inventory.contains(key2)){
                key2.setPosition(new Point(800, 200));
                key2.draw(g);
            }

        } else if (level == 3) {
            for (DisplayObject gameObject : this.getGameObjectsL3()) {
                if(gameObject != null) gameObject.draw(g);
            }

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Level N + 1: The End #", 32, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Level N + 1: The End #", 30, 30);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.GRAY);
            g2d.setBackground (Color.GRAY);
            g2d.drawString("# Inventory: #", 700, 32);

            g2d.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g2d.setColor(Color.WHITE);
            g2d.setBackground (Color.WHITE);
            g2d.drawString("# Inventory: #", 702, 30);

            key1.setPosition(new Point(800, 75));
            key1.draw(g);

            key2.setPosition(new Point(800, 200));
            key2.draw(g);

            if(inventory.contains(key3)){
                key3.setPosition(new Point(800, 300));
                key3.draw(g);
            }

            if(inventory.contains(key4)) {
                key4.setPosition(new Point(800, 400));
                key4.draw(g);
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
