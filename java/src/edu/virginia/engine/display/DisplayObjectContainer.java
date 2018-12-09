package edu.virginia.engine.display;
import java.awt.*;
import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;


public class DisplayObjectContainer extends DisplayObject {

    private ArrayList<DisplayObject> xs;

    public DisplayObjectContainer() {
        this.setPosition(new Point(0, 0));
        this.setPivotPoint(new Point(0,0));
        this.setRotation(0);
        this.setVisible(true);
        this.setAlpha(1.0f);
        this.setOldAlpha(0.0f);
        this.setScaleX(1.0);
        this.setScaleY(1.0);
        this.setFrameCount(30);
        this.setCount(0);
        this.xs = new ArrayList<DisplayObject>();
    }

    public DisplayObjectContainer(String id) {
        this.setId(id);
        this.setPosition(new Point(0, 0));
        this.setPivotPoint(new Point(0,0));
        this.setRotation(0);
        this.setVisible(true);
        this.setAlpha(1.0f);
        this.setOldAlpha(0.0f);
        this.setScaleX(1.0);
        this.setScaleY(1.0);
        this.setFrameCount(30);
        this.setCount(0);
        this.xs = new ArrayList<DisplayObject>();
    }

    public DisplayObjectContainer(String id, String fileName)
    {
        this.setId(id);
        this.setImage(fileName);
        this.setPosition(new Point(0, 0));
        this.setPivotPoint(new Point(0,0));
        this.setRotation(0);
        this.setVisible(true);
        this.setAlpha(1.0f);
        this.setOldAlpha(0.0f);
        this.setScaleX(1.0);
        this.setScaleY(1.0);
        this.setFrameCount(30);
        this.setCount(0);
        this.xs = new ArrayList<DisplayObject>();
    }

    public void addChild(DisplayObject child)
    {
        child.setParent(this);
        this.xs.add(child);
    }

    public void addChildAtIndex(DisplayObject child, int i)
    {
        child.setParent(this);
        this.xs.add(i, child);
    }

    public void removeChild(String id)
    {
        for(int i = 0; i < this.xs.size(); i++)
        {
            if(this.xs.get(i).getId().equals(id))
            {
                this.xs.remove(i);
                break;
            }
        }
    }

    public void removeByIndex(int i)
    {
        this.xs.remove(i);
    }

    public void removeAll()
    {
        this.xs.clear();
    }

    public boolean contains(DisplayObject child)
    {
        return this.contains(child);
    }

    public ArrayList<DisplayObject> getChildren()
    {
        return xs;
    }

    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        Graphics2D g2d = (Graphics2D) g;
        applyTransformations(g2d);

        if(this.xs.size() != 0) {
            for (int i = 0; i < this.xs.size(); i++) {
                this.xs.get(i).draw(g);
            }
        }

        reverseTransformations(g2d);
    }
}