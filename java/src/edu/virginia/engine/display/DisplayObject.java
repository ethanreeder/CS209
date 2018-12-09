package edu.virginia.engine.display;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import javax.imageio.ImageIO;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject {

	/* All DisplayObject have a unique id */
	private String id;
	/* Describes last position for collision resolution */
	private Point oldPosition;
	/* Describes last position for collision resolution */
	private Point oldOldPosition;
	/* Describes x, y position where object is drawn */
	private Point position;
	/* Describes linear movement speed of the object on X axis*/
	private float velocityX;
    /* Describes linear movement speed of the object on Y axis*/
    private float velocityY;
	/* Describes acceleration on positive X axis movement */
	private float accelerationXN;
	/* Describes acceleration on positive X axis movement */
	private float accelerationXP;
	/* Describes acceleration on positive Y axis movement */
	private float accelerationYN;
	/* Describes acceleration on positive Y axis movement */
	private float accelerationYP;
	/* Point relative to UI upper left corner around which object rotates*/
	private Point pivotPoint;
	/* Defines last rotation for collision resolution */
	private double oldRotation;
	/* Defines amount to rotate object */
	private double rotation;
	/* The image that is displayed by this object */
	private BufferedImage displayImage;
	/* True iff meant to be drawn*/
	private boolean visible;
	/* Describes current frame transparency */
	private float alpha;
	/* Describes previous frame transparency */
	private float oldAlpha;
	/* Describes x axis scaling */
	private double scaleX;
	/* Describes y axis scaling */
	private double scaleY;
	/* Describes last x axis scaling for collision resolution*/
	private double oldScaleX;
	/* Describes last y axis scaling for collision resolution*/
	private double oldScaleY;

	private int frameCount;

	private int count;

	private DisplayObject parent;

	private Shape hitbox;

	private boolean physics;

	private float gravity = 0.0f;

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */

	public DisplayObject()
	{
		this.position = new Point(0, 0);
        this.velocityX = 0.0f;
        this.velocityY = 0.0f;
		this.accelerationXP = 1.0f;
		this.accelerationXN = 1.0f;
		this.accelerationYP = 1.0f;
		this.accelerationYN = 1.0f;
		this.pivotPoint = new Point(0,0);
		this.rotation = 0;
		this.visible = true;
		this.alpha = 1.0f;
		this.oldAlpha = 0.0f;
		this.scaleX = 1.0;
		this.scaleY = 1.0;
		this.frameCount = 30;
		this.count = 0;
	}


	public DisplayObject(String id) {
		this.setId(id);
		this.position = new Point(0, 0);
		this.velocityX = 0.0f;
        this.velocityY = 0.0f;
		this.accelerationXP = 1.0f;
		this.accelerationXN = 1.0f;
		this.accelerationYP = 1.0f;
		this.accelerationYN = 1.0f;
		this.pivotPoint = new Point(0,0);
		this.rotation = 0;
		this.visible = true;
		this.alpha = 1.0f;
		this.oldAlpha = 0.0f;
		this.scaleX = 1.0;
		this.scaleY = 1.0;
		this.frameCount = 30;
		this.count = 0;
	}

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
		this.position = new Point(0, 0);
        this.velocityX = 0.0f;
        this.velocityY = 0.0f;
		this.accelerationXP = 1.0f;
		this.accelerationXN = 1.0f;
		this.accelerationYP = 1.0f;
		this.accelerationYN = 1.0f;
		this.pivotPoint = new Point(0,0);
		this.rotation = 0;
		this.visible = true;
		this.alpha = 1.0f;
		this.oldAlpha = 0.0f;
		this.scaleX = 1.0;
		this.scaleY = 1.0;
		this.frameCount = 30;
		this.count = 0;
	}

	/**
	 * Getters and setters
	 */
	public void setId(String id) {this.id = id;}
	public String getId() {return id;}

	public void setPosition(Point pos) {this.position = pos;}
	public Point getPosition() {return position;}

	public void setOldPosition(Point pos) {this.oldPosition = pos;}
	public Point getOldPosition() {return oldPosition;}

	public void setOldOldPosition(Point pos) {this.oldOldPosition = pos;}
	public Point getOldOldPosition() {return oldOldPosition;}

	public void setVelocityX(float a) {this.velocityX = a;}
	public float getVelocityX() {return velocityY;}

    public void setVelocityY(float a) {this.velocityX = a;}
    public float getVelocityY() {return velocityY;}

	public void setAccelerationXP(float a) {this.accelerationXP = a;}
	public float getAccelerationXP() {return accelerationXP;}

	public void setAccelerationXN(float a) {this.accelerationXN = a;}
	public float getAccelerationXN() {return accelerationXN;}

	public void setAccelerationYP(float a) {this.accelerationYP = a;}
	public float getAccelerationYP() {return accelerationYP;}

	public void setAccelerationYN(float a) {this.accelerationYN = a;}
	public float getAccelerationYN() {return accelerationYN;}

	public void setPivotPoint(Point pp) {this.pivotPoint = pp;}
	public Point getPivotPoint() {return pivotPoint;}

	public void setRotation(double r) {this.rotation = r;}
	public double getRotation() {return rotation;}

	public void setOldRotation(double r) {this.oldRotation = r;}
	public double getOldRotation() {return oldRotation;}

	public void setVisible(boolean v) {this.visible = v;}
	public boolean getVisible() {return visible;}

	public void setAlpha(float a) {this.alpha = a;}
	public float getAlpha() {return alpha;}

	public void setOldAlpha(float a) {this.rotation = a;}
	public float getOldAlpha() {return oldAlpha;}

	public void setScaleX(double s) {this.scaleX = s;}
	public double getScaleX() {return scaleX;}

	public void setScaleY(double s) {this.scaleY = s;}
	public double getScaleY() {return scaleY;}

	public void setOldScaleX(double s) {this.oldScaleX = s;}
	public double getOldScaleX() {return oldScaleX;}

	public void setOldScaleY(double s) {this.oldScaleY = s;}
	public double getOldScaleY() {return oldScaleY;}

	public void setFrameCount(int fc) {this.frameCount = fc;}
	public int getFrameCount() {return this.frameCount;}

	public void setCount(int c) {this.count = c;}
	public int getCount() {return this.count;}


	public void setParent(DisplayObject x)
	{
		this.parent = x;
	}
	public DisplayObject getParent()
	{
		return this.parent;
	}

	public void setGravity(float f)
	{
		this.gravity = f;
	}
	public float getGravity()
	{
		return this.gravity;
	}

	public void setPhysics(boolean b)
	{
		this.physics = b;
	}
	public boolean getPhysics()
	{
		return this.physics;
	}

	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}


	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}


	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	protected void update(ArrayList<Integer> pressedKeys) {

	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */

	public void draw(Graphics g) {

		if (displayImage != null && visible) {

			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */
			g2d.drawImage(displayImage, 0, 0,
					(int) (getUnscaledWidth()),
					(int) (getUnscaledHeight()), null);

			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);
		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		g2d.translate(this.position.x, this.position.y);
		//this.oldRotation = this.rotation;
		g2d.rotate(Math.toRadians(this.getRotation()), this.pivotPoint.x, this.pivotPoint.y);
		//this.oldScaleX = this.scaleX;
		//this.oldScaleY = this.scaleY;
		g2d.scale(this.scaleX, this.scaleY);
		float curAlpha;
		this.oldAlpha = curAlpha = ((AlphaComposite)g2d.getComposite()).getAlpha();
		g2d.setComposite(AlphaComposite.getInstance(3, curAlpha * this.alpha));
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		g2d.setComposite(AlphaComposite.getInstance(3, this.oldAlpha));
		g2d.scale(1/this.scaleX, 1/this.scaleY);
		g2d.rotate(Math.toRadians(-this.getRotation()), this.getPivotPoint().x,
				this.getPivotPoint().y);
		g2d.translate(-this.position.x, -this.position.y);	}


		public void jump(boolean y) {
			BufferedImage old = this.readImage(this.id + ".png");
			this.setImage("jump.png");
			if (y) {
				this.setPosition(new Point(this.getPosition().x, this.getPosition().y - 3));
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
				}
			} else {
				this.setPosition(new Point(this.getPosition().x, this.getPosition().y + 3));
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
				}
			}
		}

			public Point localToGlobal(Point p){
			if (parent == null)
				return p;
			else
				return new Point(this.getParent().getPosition().x + this.getParent().localToGlobal(p).x,
						this.getParent().getPosition().y + this.getParent().localToGlobal(p).y);
		}
			public Point globalToLocal(Point p){
			if (parent == null)
				return p;
			else
				return new Point(this.getParent().getPosition().x - this.getParent().globalToLocal(p).x,
						this.getParent().getPosition().y - this.getParent().globalToLocal(p).y);

		}
	/*
	public Point localToGlobal(Point p) {
		DisplayObject temp = null;
		while (this.parent != null) {
			temp = this.parent;
		}
		return new Point(temp.position.x - p.x, temp.position.y - p.y);
	}

	public Point globalToLocal(Point p) {
		return new Point(p.x + this.position.x, p.y + this.position.y);
	}
	*/

	public Shape getHitbox() {
		return this.hitbox;
	}
    public void setHitbox(Shape h) { this.hitbox = h; }

	public void updateHitbox() {
		Rectangle r = new Rectangle((int)(Math.round(this.getPosition().x)),
									 (int)(Math.round(this.getPosition().y)),
									 (int)(Math.round(this.getUnscaledWidth() * this.getScaleX())),
									 (int)(Math.round(this.getUnscaledHeight() * this.getScaleY())));
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(this.getRotation()), this.getPivotPoint().x + this.getPosition().x, this.getPivotPoint().y + this.getPosition().y);
		Shape newShape = at.createTransformedShape(r);
		this.hitbox = newShape;
	}

	public boolean collidesWith(DisplayObject other) {
		Shape s = other.getHitbox();
		return this.getHitbox().intersects(s.getBounds2D());
	}

	public ArrayList<DisplayObject> collidesWithAny(ArrayList<DisplayObject> objList) {
		ArrayList<DisplayObject> collisions = new ArrayList<DisplayObject>();
		for (DisplayObject obj : objList) {
			if (obj != null && this != obj) {
				if (this.collidesWith(obj)) {collisions.add(obj);}
			}
		}
		return collisions;
	}

	public void doGravity(ArrayList<DisplayObject> objList) {
		if (!this.getPhysics()) return;
		Point holdPosition = this.getPosition();
		this.setPosition(new Point(this.getPosition().x, Math.round(this.getPosition().y+this.getGravity())));
		this.updateHitbox();
		if (!this.collidesWithAny(objList).isEmpty()) {
			this.setPosition(holdPosition);
			this.updateHitbox();
			this.setGravity(0.0f);
		} else {
			this.setGravity(this.getGravity() + 0.5f);
		}
	}

	public ArrayList<DisplayObject> tryMove(int xChange, int yChange, ArrayList<DisplayObject> objList) {
		ArrayList<DisplayObject> collisions = new ArrayList<DisplayObject>();
		Point holdPosition = this.getPosition();
		if (!this.collidesWithAny(objList).isEmpty()) {
			this.setPosition(new Point(this.getPosition().x + xChange, this.getPosition().y + yChange));
			this.updateHitbox();
            collisions = this.collidesWithAny(objList);
			if (!collisions.isEmpty()) {
				this.setPosition(holdPosition);
				this.updateHitbox();
			} else {
				this.setPosition(holdPosition);
				this.updateHitbox();
				for (DisplayObject obj : objList) {
					if (obj != null && this != obj) {
						obj.setPosition(new Point(obj.getPosition().x - xChange, obj.getPosition().y - yChange));
					}
				}
			}
		} else {
			this.setPosition(new Point(this.getPosition().x + xChange, this.getPosition().y + yChange));
			this.updateHitbox();
			collisions = this.collidesWithAny(objList);
			if (!collisions.isEmpty()) {
				this.setPosition(new Point(holdPosition));
				this.updateHitbox();
			} else {
				this.setPosition(holdPosition);
				this.updateHitbox();
				for (DisplayObject obj : objList) {
					if (obj != null && this != obj) {
						obj.setPosition(new Point(obj.getPosition().x - xChange, obj.getPosition().y - yChange));
					}
				}
			}
		}
		return collisions;
	}
}
