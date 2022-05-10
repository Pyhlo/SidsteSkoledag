package me.pyhlo;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;

public class Checkpoint {
    public int index;
    private int maxIndexes;
    public String songpath;
    public String type;

    public double startpoint;
    public double endpoint; //the double is in millieseconds

    public Checkpoint(File f, int starting_index) throws IOException {
        index = starting_index;
        JsonObject obj = Utils.getElement(f).getAsJsonObject();
        maxIndexes = obj.size();
        JsonObject inObj = obj.get(String.valueOf(index)).getAsJsonObject();
        type = inObj.get("type").getAsString();
        if (type.equalsIgnoreCase("stop")) {
            endpoint = inObj.get("end").getAsDouble();
            songpath = inObj.get("songpath").getAsString();
        } else {
            startpoint = inObj.get("start").getAsDouble();
            endpoint = inObj.get("end").getAsDouble();
            songpath = inObj.get("songpath").getAsString();
        }
    }

    public int next() throws IOException { //returns & switches to the next checkpoint
        index++;
        System.out.println("next " + index);
        JsonObject obj = Utils.getElement(Main.f).getAsJsonObject();
        System.out.println("before inObj init");
        JsonObject inObj = obj.get(String.valueOf(index)).getAsJsonObject();
        System.out.println("before type");
        type = inObj.get("type").getAsString();
        System.out.println("still works");
        if (type.equalsIgnoreCase("stop")) {
            System.out.println("choose type 'stop'");
            endpoint = inObj.get("end").getAsDouble();
            songpath = inObj.get("songpath").getAsString();
        } else {
            startpoint = inObj.get("start").getAsDouble();
            endpoint = inObj.get("end").getAsDouble();
            songpath = inObj.get("songpath").getAsString();
        }
        return index;
    }

    public int previous() { //returns & switches to the previous checkpoint
        return 0;
    }

    public void setIndex(int in) { //sets the current checkpoint index
        index = in;
    }

}
