package me.pyhlo;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Sound {
    static AudioPlayer aPlayer = AudioPlayer.player;
    static AudioStream aStream;

    public static boolean isPlaying;

    public static void play(String path) {
        if (isPlaying) { aPlayer.stop(aStream); }
        try {
            isPlaying = true;
            aStream = new AudioStream(new FileInputStream(path));
            aPlayer.start(aStream);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public static void stop() {
        isPlaying = false;
        //send to server console
        aPlayer.stop(aStream);
    }

    public static void resume() {

    }

    public static void pause() {

    }

}
