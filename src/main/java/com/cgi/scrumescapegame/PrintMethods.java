package com.cgi.scrumescapegame;

import java.io.IOException;
import java.util.Random;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class PrintMethods {
	public static void clearScreen() {
        if(Game.debug) return;

        System.out.print("\033\143");

        try {
            if (Game.isWindows) {
                // For Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            if (Game.debug) System.err.println("Error clearing console: " + e.getMessage());
        }
    }

	public static void typeText(String text) {
		if(Game.debug) {
			System.out.println(text);
			return;
		}
        Random random = new Random();
        StringBuilder currentText = new StringBuilder();
    
        for (int i = 0; i < text.length(); i++) {
            char targetChar = text.charAt(i);
    
            if (i > 0) {
                long animationDuration = 30;
                long switchInterval = 17;
                long elapsed = 0;
    
                while (elapsed < animationDuration) {
                    char randomChar = (char) (random.nextInt(94) + 33);
                    System.out.print("\r" + currentText.toString() + randomChar);
                    System.out.flush();
    
                    long sleepTime = switchInterval;
                    if (elapsed + switchInterval > animationDuration) {
                        sleepTime = animationDuration - elapsed;
                    }
                    
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    elapsed += sleepTime;
                }
            }
    
            currentText.append(targetChar);
            System.out.print("\r" + currentText.toString());
            System.out.flush();
        }
		System.out.println();
    }

    public static void printColor(String text, Attribute colorAttribute) {
        System.out.print(Ansi.colorize(text, colorAttribute));
    }

    public static void printlnColor(String text, Attribute colorAttribute) {
        System.out.println(Ansi.colorize(text, colorAttribute));
    }
}
