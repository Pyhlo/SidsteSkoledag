package me.pyhlo.Events;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import me.pyhlo.Main;
import me.pyhlo.Sound;

import java.io.IOException;

public class KeyEvents {
    public static void attachKeyEvents(Scene scene, final Stage stage, MediaPlayer player) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            // Forward the video towards the next checkpoint
            if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.ENTER) {
                if (Main.waitForCommand) {
                    //System.out.println("indtil videre");
                    if (!Main.cutto) {
                        Main.waitForCommand = false;
                        Main.player.play();
                        try {
                            Main.checkpoint.next();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Main.currentThread = new Main.Multithreading();
                        Main.currentThread.start();
                        if (Sound.isPlaying) {
                            Sound.stop();
                        }
                        System.out.println("NEXT!");
                    } else {
                        Main.waitForCommand = false;
                        Main.cutto = false;
                        double endpoint = Main.checkpoint.endpoint;
                        Main.player.seek(new javafx.util.Duration(endpoint));
                        if (Sound.isPlaying) {
                            Sound.stop();
                        }
                        if (Main.player.getStatus() == MediaPlayer.Status.PAUSED) {
                            Main.player.play();
                        }
                        System.out.println("Skipping to endpoint: " + endpoint);
                        try {
                            Main.checkpoint.next();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Main.currentThread = new Main.Multithreading();
                        Main.currentThread.start();
                        System.out.println("CUTTO!");
                    }
                    //send to server console
                }
            }

            //Previous checkpoint
            else if (event.getCode() == KeyCode.LEFT && event.isShiftDown()) {
                //find a way to change were we currently are in the video (to rollback the video)
                System.out.println("PREVIOUS!");
            }

            // Pause
            else if (event.getCode() == KeyCode.ESCAPE && event.isShiftDown()){
                if (!Main.waitForCommand){
                    if (Main.paused) {
                        player.play();
                        System.out.println("RESUME!");
                        if (Sound.isPlaying) {
                            Sound.resume();
                        }
                        Main.paused = false;
                    } else {
                        player.pause();
                        System.out.println("PAUSE!");
                        if (Sound.isPlaying) {
                            Sound.pause();
                        }
                        Main.paused = true;
                    }
                }
            }
        });
    }
}
