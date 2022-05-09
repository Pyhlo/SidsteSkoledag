package me.pyhlo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class Utils {
    public static double getNext(double current, JsonElement el) {
        if (current == 0) {
            System.out.println("next: " + el.getAsString());
        }
        return 200;
    }

    public static JsonElement getJsonText(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        String text = IOUtils.toString(is, StandardCharsets.UTF_8);
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(text, JsonElement.class);
    }
}
