/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author thao
 */
public class TestLesson implements LineListener{
    private int id;
    private String name;
    private long length;
    private int level;
    private String demoScript;
    private String mp3Url;
    private ArrayList<String[]> script;
    private ArrayList<String> hint;
    private boolean playCompleted;
    private Clip audioClip;

    public Clip getAudioClip() {
        return audioClip;
    }

    public TestLesson(int id, String name, long length, int level, String demoScript, String mp3Url, ArrayList<String[]> script, ArrayList<String> hint) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.level = level;
        this.demoScript = demoScript;
        this.mp3Url = mp3Url;
        this.script = script;
        this.hint = hint;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public ArrayList<String> getHint() {
        return hint;
    }

    public void setHint(ArrayList<String> hint) {
        this.hint = hint;
    }

    public void setAudioClip(Clip audioClip) {
        this.audioClip = audioClip;
    }
    
    
    public boolean isPlayCompleted() {
        return playCompleted;
    }

    public void setPlayCompleted(boolean playCompleted) {
        this.playCompleted = playCompleted;
    }
    
    public TestLesson(){
        
    }

    public TestLesson(String name, long length, int level, String demoScript, String mp3Url, ArrayList<String[]> script, boolean playCompleted, Clip audioClip) {
        this.name = name;
        this.length = length;
        this.level = level;
        this.demoScript = demoScript;
        this.mp3Url = mp3Url;
        this.script = script;
        this.playCompleted = playCompleted;
        this.audioClip = audioClip;
    }

    public ArrayList<String[]> getScript() {
        return script;
    }

    public void setScript(ArrayList<String[]> script) {
        this.script = script;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDemoScript() {
        return demoScript;
    }

    public void setDemoScript(String demoScript) {
        this.demoScript = demoScript;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }
    
    public void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(Clip.class, format);
 
            this.audioClip = (Clip) AudioSystem.getLine(info);
 
            audioClip.addLineListener(this);
 
            audioClip.open(audioStream);
     
            audioClip.start();
             
            while (!playCompleted) {
                // wait for the playback completes
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    this.playCompleted = true;
                }
            }
             
            audioClip.close();
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
         
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
         
        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");
             
        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }
    }
    
}
