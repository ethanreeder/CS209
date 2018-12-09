package edu.virginia.engine.display;
import java.awt.*;
import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;


public class Dial extends DisplayObject {
    private ArrayList<AnimatedSprite> faces;
    private AnimatedSprite dial;
    private AnimatedSprite match;
    private AnimatedSprite onIndicator;
    private boolean on;

    public Dial(String match, String face1, String face2, String face3, Point pt) {
        this.dial = new AnimatedSprite("dial", "dial.png", new Point(pt.x, pt.y));
        this.match = new AnimatedSprite("dial", match, new Point(pt.x, pt.y));
        this.faces.add(0, new AnimatedSprite("face1", face1, new Point(pt.x, pt.y)));
        this.faces.add(0, new AnimatedSprite("face2", face2, new Point(pt.x, pt.y)));
        this.faces.add(0, new AnimatedSprite("face3", face3, new Point(pt.x, pt.y)));
        this.onIndicator = new AnimatedSprite("onIndicator", "onIndicator.png", new Point(pt.x, pt.y));
    }

    public void rotate(int direction) {
        int length = faces.size();
        if (direction > 0) {
            for (int i = 0; i < direction; i ++) {
                AnimatedSprite tempObj = faces.get(length - 1);
                faces.remove(length - 1);
                faces.add(0, tempObj);
            }
        } else if (direction < 0) {
            for (int i = 0; i > direction; i --) {
                AnimatedSprite tempObj = faces.get(0);
                faces.remove(0);
                faces.add(length - 1, tempObj);
            }
        } else {
            ;
        }
    }

    public boolean checkAlignment() {
        if (faces.get(0).getFileName() == match.getFileName()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(Graphics g) {
        if (super.getDisplayImage() != null && super.getVisible()) {

            super.draw(g);
            Graphics2D g2d = (Graphics2D) g;
            applyTransformations(g2d);

            for (AnimatedSprite obj : faces) {
                if (obj != null) {
                    obj.draw(g);
                }
            }

            if (dial != null) { dial.draw(g); }
            if (match != null) { match.draw(g); }
            if (onIndicator != null && on) { onIndicator.draw(g); }

            reverseTransformations(g2d);
        }
    }

}
