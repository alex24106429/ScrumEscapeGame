package com.cgi.scrumescapegame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class FileHandler {
    public static final String savepath = Paths.get(System.getProperty("user.home"), "ScrumEscapeGameData").toString();

    public static void writeFile(String data, String filename) {
        if(Game.isScrumOS) {
            PrintMethods.printlnColor("Opslaan is niet beschikbaar in deze omgeving.", Attribute.BRIGHT_RED_TEXT());
            return;
        };
        try {
            new File(savepath).mkdirs();
            String filepath = Paths.get(savepath, filename).toString();
            FileWriter myWriter = new FileWriter(filepath);
            myWriter.write(data);
            myWriter.close();
            PrintMethods.printlnColor("Gegevens opgeslagen in: " + filepath, Attribute.BRIGHT_GREEN_TEXT());
        } catch (IOException e) {
            PrintMethods.printlnColor("Er is iets misgegaan tijdens het opslaan: ", Attribute.BRIGHT_RED_TEXT());
            e.printStackTrace();
        }
    }
}
