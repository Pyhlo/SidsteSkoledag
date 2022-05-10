package me.pyhlo.Events;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import me.pyhlo.Main;

import java.io.IOException;

public class KeyEvents {
    public static void attachKeyEvents(Scene scene, final Stage stage, MediaPlayer player) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            // Forward the video towards the next checkpoint
            if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.ENTER) {
                if (Main.waitForCommand) {
                    Main.waitForCommand = false;
                    Main.player.play();
                    try {
                        Main.checkpoint.next();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    /*Main.Multithreading obj = new Main.Multithreading();
                    obj.start();*/
                    System.out.println("NEXT!");
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
                        Main.paused = false;
                    } else {
                        player.pause();
                        System.out.println("PAUSE!");
                        Main.paused = true;
                    }
                }
            }
        });
    }
}
