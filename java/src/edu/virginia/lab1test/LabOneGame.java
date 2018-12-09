package edu.virginia.lab1test;

import java.awt.*;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabOneGame extends Game{

	/* Create a sprite object for our game. We'll use mario */
	Sprite mario = new Sprite("Mario", "Mario.png");
	boolean vlast = false;
	
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabOneGame() {
		super("Lab One Test Game", 500, 300);
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
			} else {
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
		LabOneGame game = new LabOneGame();
		game.start();

	}
}
