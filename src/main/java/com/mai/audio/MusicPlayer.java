package com.mai.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer {

    private static Clip clip;

    public static void setMusic(InputStream stream) {
        // fix for mark/reset error when compiled to jar
        BufferedInputStream bis = new BufferedInputStream(stream);

        try {
            if(clip != null) clip.stop();
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bis);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            System.out.println("Something went wrong while trying to play music");

            e.printStackTrace();
        }
    }

    public static void playMusic(){
        clip.start();
    }

    public static void loopMusic(){
        try{
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception possibleNullPointer){
            System.out.println("Something went wrong while trying to play music");

            possibleNullPointer.printStackTrace();
        }
    }

    public static void stopMusic(){
        try{
            clip.stop();
        } catch (Exception possibleNullPointer){
            System.out.println("Something went wrong while trying to play music");

            possibleNullPointer.printStackTrace();
        }
    }
}
