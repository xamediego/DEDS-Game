package com.mai.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer {
    public static void playAudioFile(InputStream stream){
        // fix for mark/reset error when compiled to jar
        BufferedInputStream bis = new BufferedInputStream(stream);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bis);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            System.out.println("Something went wrong while reading this audio file \n" + e);

            e.printStackTrace();
        }
    }
}
