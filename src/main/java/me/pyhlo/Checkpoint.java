package me.pyhlo;

import java.nio.file.Path;
import java.time.Duration;

public class Checkpoint {
    private int index;
    private Path songpath;
    private double startpoint;
    private double endpoint; //the double is in millieseconds

    public double getStartpoint() { //gets the starting point of the current checkpoint
        return startpoint;
    }

    public double getEndpoint() { //gets the ending point of the current checkpoint
        return endpoint;
    }

    public Path getSongPath() { //gets the path to the song currently being played (if any)
        return songpath;
    }

    public int next() { //returns & switches to the next checkpoint
        return 0;
    }

    public int previous() { //returns & switches to the previous checkpoint
        return 0;
    }

    public void setIndex(int in) { //sets the current checkpoint index
        index = in;
    }

}
