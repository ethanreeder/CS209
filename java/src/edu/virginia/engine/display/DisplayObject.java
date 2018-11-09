package edu.virginia.engine.display;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import javax.imageio.ImageIO;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject{

	/* All DisplayObject have a unique id */
	private String id;
	/* Describes x, y position where object is drawn */
	private Point position;
	/* Point relative to UI upper left corner around which object rotates*/
	private Point pivotPoint;
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

	private int frameCount;

	private int count;

	private DisplayObject parent;
	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */

	public DisplayObject()
	{
		this.position = new Point(0, 0);
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

	public void setPivotPoint(Point pp) {this.pivotPoint = pp;}
	public Point getPivotPoint() {return pivotPoint;}

	public void setRotation(double r) {this.rotation = r;}
	public double getRotation() {return rotation;}

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
		g2d.rotate(Math.toRadians(this.getRotation()), this.pivotPoint.x, this.pivotPoint.y);
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
//*
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
			//*/
			/*
			for(int i = 0; i < 5; i++)
			{
				this.setPosition(new Point(this.getPosition().x, this.getPosition().y-1));
				try
				{
					Thread.sleep(2);
				}
				catch(InterruptedException e)
				{
					;
				}
			}



			for(int i = 0; i < 5; i++)
			{
				this.setPosition(new Point(this.getPosition().x, this.getPosition().y+1));
				try
				{
					Thread.sleep(2);
				}
				catch(InterruptedException e)
				{
					;
				}
			}
			*/



//*
	public Point localToGlobal(Point p)
	{
		if (parent == null)
				return p;
		else
			return new Point(this.getParent().getPosition().x + this.getParent().localToGlobal(p).x,
					this.getParent().getPosition().y + this.getParent().localToGlobal(p).y);
	}

	public Point globalToLocal(Point p)
	{
		if (parent == null)
			return p;
		else
			return new Point(this.getParent().getPosition().x - this.getParent().globalToLocal(p).x,
					this.getParent().getPosition().y - this.getParent().globalToLocal(p).y);

	}
//*/
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

	//*
	public Shape getHitbox(DisplayObject x)
	{
		AffineTransform tx = new AffineTransform();
		tx.rotate(x.getRotation());
		Rectangle hit = new Rectangle((int)Math.round(x.getUnscaledHeight()*x.scaleY), (int)Math.round(x.getUnscaledWidth()*x.scaleX),
				(int)x.getPosition().getX(), (int)x.getPosition().getY());
		Shape box = tx.createTransformedShape(hit);
		return box;
	}
	//*/

}
