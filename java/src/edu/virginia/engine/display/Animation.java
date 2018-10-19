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
