<<<<<<< HEAD
package edu.virginia.engine.display;

public class Animation {
    /* Name of the animation */
    private String id;
    /* Index of first frame in list of images */
    private Integer startFrame;
    /* Index of last frame in list of images */
    private Integer endFrame;

    public Animation (String id, int startFrame, int endFrame) {
        this.id = id;
        this.startFrame = startFrame;
        this.endFrame = endFrame;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getStartFrame() { return startFrame; }
    public void setStartFrame(int startFrame) { this.startFrame = startFrame; }

    public int getEndFrame() { return endFrame; }
    public void setEndFrame(int endFrame) { this.endFrame = endFrame; }



}
=======
package edu.virginia.engine.display;

public class Animation {

    // name of animation
    private String id;

    // index of start frame in list of images
    private int startFrame;

    // index of last frame in list of images
    private int endFrame;


    // Constructor
    public Animation(String id, int startFrame, int endFrame)
    {
        this.id = id;
        this.startFrame = startFrame;
        this.endFrame = endFrame;
    }

    // Setters
    public void setId(String id)
    {
        this.id = id;
    }

    public void setStartFrame(int startFrame)
    {
        this.startFrame = startFrame;
    }

    public void setEndFrame(int endFrame)
    {
        this.endFrame = endFrame;
    }


    // Getters

    public String getId()
    {
        return this.id;
    }

    public int getStartFrame()
    {
        return this.startFrame;
    }

    public int getEndFrame()
    {
        return this.endFrame;
    }




}
>>>>>>> 7ffa039da64265ab8a066cc4c8d945b3f03e72a9
