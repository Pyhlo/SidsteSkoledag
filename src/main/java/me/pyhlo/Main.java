package me.pyhlo;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ExecutionException;

import com.google.gson.JsonElement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import me.pyhlo.Events.KeyEvents;

/*
TODO:
- Make another program (exactly the same as this, but it's starting checkpoint is lastCheckpoint.txt's checkpoint)
 */

public class Main extends Application {
    public static Checkpoint checkpoint;

    public static Media media;
    public static MediaPlayer player;

    public static File f;
    public static File j;

    //private static double startpoint;
    //private static double endpoint;

    public static boolean paused = false;

    public static boolean waitForCommand = false;
    public static boolean cutto = false;

    public static Multithreading currentThread;

    public static void main(String[] args) throws IOException {
        f = new File(new File(".").getCanonicalPath() + "\\runme.mp4");
        j = new File(new File(".").getCanonicalPath() + "\\checkpoints.json");
        if (!j.exists()) {
            System.out.println("No checkpoints.json file found.");
            return;
        } if (!f.exists()) {
            System.out.println("No runme.mp4 file found.");
            return;
        }
        checkpoint = new Checkpoint(j, 0);

        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("Starting \"sidste skoledags\" video, please stand by...");
        //JsonElement json = Utils.getJsonText(j);
        //double first = Utils.getNext(currentTimePause, json);
        media = new Media(f.toURI().toURL().toString());
        //System.out.println("Length of video: " + media.durationProperty().getValue().toSeconds() + " seconds");
        player = new MediaPlayer(media);

        MediaView view = new MediaView(player);

        Scene scene = new Scene(new Pane(view), 1920, 1080);
        KeyEvents.attachKeyEvents(scene, stage, player);
        stage.setScene(scene);
        stage.show();
        //stage.setAlwaysOnTop(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreen(true);
        stage.setTitle("SIDSTE SKOLEDAG 2022!!");
        player.play();
        player.setOnEndOfMedia(() -> { //remove this later
            stage.close();
            System.out.println("DONE!");
        });
        currentThread = new Multithreading();
        currentThread.start();

        //MAKE THIS SHIT WORK WITHOUT DISTURBING THE MAIN LOOP
        /*Future<Integer> future = waitForCheckpoint(5000);
        while (!future.isDone()) {
            System.out.println("Waiting...");
        }
        Integer result = future.get();

        System.out.println(result);
           */
        //run checkForCheckpoint's method async & wait for it's callback

    }

    public static class Multithreading extends Thread{
        public void run() {
            try {
                System.out.println("running thread");
                sleep(200);
                double current = player.getCurrentTime().toMillis();
                System.out.println(current);
                if (current >= checkpoint.endpoint) {
                    if (checkpoint.type.equalsIgnoreCase("stop")) {
                        player.pause();
                        //player.seek(new javafx.util.Duration(4500)); //this shit will force it to fastfoward yeah yeah
                        waitForCommand = true;
                        if (checkpoint.hasAudio) {
                            Sound.play(checkpoint.songpath);
                        }
                        //send to server console
                    }
                } else if (checkpoint.type.equalsIgnoreCase("cutto")) {
                    if (current >= checkpoint.startpoint) {
                        cutto = true;
                        waitForCommand = true;
                        if (checkpoint.hasAudio) {
                            Sound.play(checkpoint.songpath);
                        }
                        System.out.println("cutto??");
                    } else {
                        currentThread = new Multithreading();
                        currentThread.start();
                    }
                    //send to server console
                } else {
                    currentThread = new Multithreading();
                    currentThread.start();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


