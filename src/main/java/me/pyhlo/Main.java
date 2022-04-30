package me.pyhlo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaErrorEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import me.pyhlo.Events.KeyEvents;

public class Main extends Application {
    /*Media media;

    {
        try {
            media = new Media(new File(new File(".").getCanonicalPath() + "\\runme.mp4").toURI().toURL().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static boolean paused = false;

    public static void main(String[] args) {
        System.out.println("Main!");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, InterruptedException, ExecutionException {
        System.out.println("Starting \"sidste skoledags\" video, please stand by...");
        File f = new File(new File(".").getCanonicalPath() + "\\runme.mp4");
        Media media = new Media(f.toURI().toURL().toString());
        //System.out.println("Length of video: " + media.durationProperty().getValue().toSeconds() + " seconds");
        MediaPlayer player = new MediaPlayer(media);

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
        Multithreading obj = new Multithreading();
        obj.start();

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
                sleep(2000);
                System.out.println("ETER 2000 MILLISECONDS????");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


