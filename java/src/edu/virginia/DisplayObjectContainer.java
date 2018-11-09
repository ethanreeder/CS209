package edu.virginia;

import edu.virginia.engine.display.DisplayObject;

import java.awt.*;
import java.util.ArrayList;


public class DisplayObjectContainer extends DisplayObject {

    private ArrayList<DisplayObject> Children;

    public void addChild(DisplayObject child) {
        child.setParent(this);
        Children.add(0, child);
    }

    public void addChildAtIndex(DisplayObject child, int i) {
        child.setParent(this);
        Children.add(i, child);
    }

    public void removeChild(String id) {
        for (int counter = 0; counter < Children.size(); counter++) {
            if (Children.get(counter).getId().equals(id)) {
                Children.remove(counter);
                break;
            }
        }
    }

    public void removeByIndex(int i) {
        Children.remove(i);
    }

    public void removeAll() {
        Children.clear();
    }

    public boolean contains(DisplayObject child) {
        if (Children.contains(child)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<DisplayObject> getChildren() {
        return Children;
    }

    @Override
    public void draw(Graphics g) {

        super.draw(g);
        Graphics2D g2d = (Graphics2D) g;
        applyTransformations(g2d);

        for (DisplayObject child : this.Children) {
            child.draw(g);
        }

        reverseTransformations(g2d);
    }
}
