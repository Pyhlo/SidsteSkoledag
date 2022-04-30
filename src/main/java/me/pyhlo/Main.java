package me.pyhlo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    /*Media media;

    {
        try {
            media = new Media(new File(new File(".").getCanonicalPath() + "\\runme.mp4").toURI().toURL().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        System.out.println("Starting \"sidste skoledags\" video, please stand by...");
        File f = new File(new File(".").getCanonicalPath() + "\\runme.mp4");
        Media media = new Media(f.toURI().toURL().toString());

        MediaPlayer player = new MediaPlayer(media);

        MediaView view = new MediaView(player);

        Scene scene = new Scene(new Pane(view), 1920, 1080);
        stage.setScene(scene);
        stage.show();
        //stage.setAlwaysOnTop(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreen(true);
        stage.setTitle("SIDSTE SKOLEDAG 2022!!");
        player.play();
        Thread.sleep(1000);
    }

}
