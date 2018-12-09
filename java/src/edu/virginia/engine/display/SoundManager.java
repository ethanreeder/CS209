package edu.virginia.engine.display;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import javax.sound.sampled.*;

public class SoundManager {

    private HashMap hm = new HashMap();

    public SoundManager(String id)
    {
        hm.put("readygo", "readygo.wav");
        hm.put("jump", "jump.wav");
        hm.put("bump", "bump.wav");
        hm.put("win", "win.wav");
        hm.put("loss", "loss.wav");
        hm.put("marioMusic", "marioMusic.wav");


        LoadSoundEffect(id, (String)hm.get(id));

        if(id.equals("marioMusic"))
            PlayMusic(id);
        else
            PlaySoundEffect(id);

    }
    private Clip clip;

    public void LoadSoundEffect(String id, String filename)
    {
        String path = "resources/" + filename;
        try {
            File soundFile = new File(path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();
            clip.open(audioIn);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void PlaySoundEffect(String id)
    {
        if (clip.isRunning())
            clip.stop();
        clip.setFramePosition(0);

        clip.start();
       // clip.loop(5);

    }

    public void LoadMusic(String id, String filename)
    {
        LoadSoundEffect(id, filename);
    }

    public void PlayMusic(String id)
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


}