package me.pyhlo;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Checkpoint {
    public int index;
    public String songpath;
    public String type;

    public boolean hasAudio;

    public double startpoint;
    public double endpoint; //the double is in millieseconds

    private final PrintWriter writer = new PrintWriter(new FileOutputStream("lastCheckpoint.txt", false));

    public Checkpoint(File f, int starting_index) throws IOException {
        index = starting_index;
        JsonObject obj = Utils.getElement(f).getAsJsonObject();
        JsonObject inObj = obj.get(String.valueOf(index)).getAsJsonObject();
        System.out.println("Starting checkpoint 0...\nNumber of checkpoints: " + obj.size());
        type = inObj.get("type").getAsString();
        if (!type.equalsIgnoreCase("stop")) {
            startpoint = inObj.get("start").getAsDouble();
        }
        endpoint = inObj.get("end").getAsDouble();
        songpath = inObj.get("songpath").getAsString();
        hasAudio = !songpath.equals("null");
    }

    public int next() throws IOException { //returns & switches to the next checkpoint
        index++;
        System.out.println("next " + index);
        writer.write(String.valueOf(index));
        writer.flush();
        try {
            JsonObject obj = Utils.getElement(Main.j).getAsJsonObject();
            JsonObject inObj = obj.get(String.valueOf(index)).getAsJsonObject();
            type = inObj.get("type").getAsString();
            if (!type.equalsIgnoreCase("stop")) {
                startpoint = inObj.get("start").getAsDouble();
            }
            endpoint = inObj.get("end").getAsDouble();
            songpath = inObj.get("songpath").getAsString();
            hasAudio = !songpath.equals("null");
            System.out.println("next id: " + index + "\nstart: " + startpoint + "\nend: " + endpoint + "\ntype: " + type);
            return index;
        } catch (NullPointerException err) {
            System.out.println("No more checkpoints!");
            Main.currentThread.interrupt();
        }
        return -1;
    }

    public int previous() { //returns & switches to the previous checkpoint
        return 0;
    }

    public void setIndex(int in) { //sets the current checkpoint index
        index = in;
    }

}
