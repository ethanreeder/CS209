package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimatedSprite extends Sprite {
    ///*
    private ArrayList<Animation> animations;
    private boolean playing;
    private String fileName;
    private ArrayList<BufferedImage> frames;
    private Integer currentFrame;
    private Integer startFrame;
    private Integer endFrame;
    private static final int DEFAULT_ANIMATION_SPEED = 3;
    private Integer animationSpeed;
    private GameClock gameClock;
    private int frameCount;
    private boolean paused;


    public AnimatedSprite (String id, String filename, Point position) {

        super(id);
        this.setFrameCount(30);
        this.initGameClock();
        this.setId(id);
        this.setPosition(position);
        this.setImage(filename);
        this.setAnimationSpeed(DEFAULT_ANIMATION_SPEED);
        this.startFrame = 0;
        this.endFrame = 0;
        this.currentFrame = 0;
        this.frames = new ArrayList<BufferedImage>();
        this.animations = new ArrayList<Animation>();
        this.playing = false;
        this.paused = false;
        this.setCount(0);
    }


    public void setStartFrame(int sF) {this.startFrame = sF;}
    public int getStartFrame() {return this.startFrame;}
    public void setEndFrame(int eF) {this.endFrame = eF;}
    public int getEndFrame() {return this.endFrame;}
    public void setCurrentFrame(int cF) {this.currentFrame = cF;}
    public int getCurrentFrame() {return this.currentFrame;}

    public void setCount(int c) {this.frameCount = c;}
    public int getCount() {return this.frameCount;}

    public void setPlaying(boolean b) {this.playing = b;}
    public Boolean getPlaying() {return this.playing;}

    public void setPaused(boolean b) {this.paused = b;}
    public Boolean getPaused() {return this.paused;}

    public void setAnimationSpeed(int speed) {
        this.animationSpeed = speed;
    }

    public int getAnimationSpeed() {
        return this.animationSpeed;
    }

    public void addFrame(String imageName) {
        if (imageName == null) {
            return;
        }
        BufferedImage frame = readImage(imageName);

        if (frame!= null) {
            this.frames.add(frame);
        }
    }

    public Animation getAnimation(String id){
        for (int i = 0; i < this.animations.size(); i++) {
            if(animations.get(i).getId().equals(id)) {
                return animations.get(i);
            }

        }
        return null;
    }
    public void setAnimations(Animation an){
        this.animations.add(an);
    }

    public void setAnimationSpeed(Integer animationSpeed)
    {
        this.animationSpeed = animationSpeed;
    }


    /*
    public void draw()
    {
        this.gameClock.resetGameClock();
    }
    */

    public void initGameClock() {
        if (gameClock == null) {
            this.gameClock = new GameClock();
        }
    }//*/

    private void animate(Animation a){
        this.setStartFrame(a.getStartFrame());
        this.setEndFrame(a.getEndFrame());
    }

    public void animate(String id) {
        this.setStartFrame(getAnimation(id).getStartFrame());
        this.setEndFrame(getAnimation(id).getEndFrame());
    }



    public void animate(int start, int end) {
        this.setStartFrame(start);
        this.setEndFrame(end);
    }

    public void stopAnimation(int fNum) {
        this.setPaused(true);
        this.startFrame = fNum;
    }

    public void stopAnimation(){
        stopAnimation(this.startFrame);
    }

    public void draw(Graphics g)
    {
        int startFrame = this.getStartFrame();
        int endFrame = this.getEndFrame();
        int currentFrame = this.getCurrentFrame();
        BufferedImage frame;

        if (playing == true) {
            if (super.getFrameCount() == this.animationSpeed) {

                frame = this.frames.get(currentFrame);
                if (currentFrame == endFrame) {
                    this.setCurrentFrame(startFrame - 1);
                }
                this.setCurrentFrame(this.getCurrentFrame() + 1);
                super.setFrameCount(0);
            } else {
                frame = this.frames.get(currentFrame);
            }
            if(this.getCurrentFrame() == this.getEndFrame()){
                setPlaying(false);
            }
        } else {
            frame = getDisplayImage();
        }

        if (frame != null) {
            Graphics2D g2d = (Graphics2D) g;

            applyTransformations(g2d);
            /* Actually draw the image, perform the pivot point translation here */
            if (super.getVisible()) {
                g2d.drawImage(frame, 0, 0, (int) (getUnscaledWidth()),
                        (int) (getUnscaledHeight()), null);
            }

            /*
             * undo the transformations so this doesn't affect other display
             * objects
             */
            reverseTransformations(g2d);
        }
    }



}