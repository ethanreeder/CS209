package edu.virginia.engine.display;
import edu.virginia.engine.util.GameClock;

import javafx.animation.Animation;
import java.awt.*;
import java.util.ArrayList;

public class AnimatedSprite extends Sprite {
    /* ArrayList of animation objects for the animated sprite */
    private ArrayList<Animation> animations;
    /* Boolean describing whether or not the sprite is being animated */
    private boolean playing;
    /* The file containing the images for the animation */
    private String fileName;
    /* ArrayList of images to draw for the animation */
    private ArrayList<Image> frames;
    /* Index of current image being drawn */
    private Integer currentFrame;
    /* Index of first image to draw for the animation */
    private Integer startFrame;
    /* Index of end image to draw for the animation */
    private Integer endFrame;
    /* Default speed at which images change */
    private static final Integer DEFAULT_ANIMATION_SPEED;
    /* Speed at which images change */
    private Integer animationSpeed;
    /* Gameclock for this sprite */
    private GameClock gameClock;

    public AnimatedSprite (String id, String filename, Point position) {
        super(id);
        this.fileName = fileName;
        super(position) = position;
        this.gameClock = new GameClock();
        this.animationSpeed = 1;
    }

    public Integer getAnimationSpeed() { return animationSpeed; }
    public void setAnimationSpeed(Integer animationSpeed) { this.animationSpeed = animationSpeed; }

    public void initGameClock() {
        if (gameClock == null) {
            this.gameClock = new GameClock();
        }
    }

    public void draw(Graphics g) {
        //check if playing and elapsed time, set image to next frame
        if (gameClock == null) {
            this.gameClock = new GameClock();
        }

        if (playing && gameClock.getElapsedTime() > 10) {
            if (currentFrame == endFrame) {
                this.currentFrame = startFrame;
            } else {
                this.currentFrame += 1;
            }
            setImage(frames.get(currentFrame));
        }
        this.gameClock.resetGameClock();

        super.draw(g);
    }

    public void populateFrames()
}