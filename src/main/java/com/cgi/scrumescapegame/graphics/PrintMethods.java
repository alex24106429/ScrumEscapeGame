package com.cgi.scrumescapegame.graphics;

import java.io.IOException;
import java.util.Random;

import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.items.EquipableItem;
import com.cgi.scrumescapegame.items.Item;
import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class PrintMethods {
    public static void clearScreen() {
        if (Game.debug)
            return;

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
            if (Game.debug)
                System.err.println("Error clearing console: " + e.getMessage());
        }
    }

    public static void typeText(String text) {
        if (Game.debug) {
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

    public static String getProgressBarString(int percentage, int width) {
        if (width <= 0) {
            return "";
        }
        percentage = Math.max(0, Math.min(100, percentage));

        char[] blocks = {
                '▏',
                '▎',
                '▍',
                '▌',
                '▋',
                '▊',
                '▉',
                '█'
        };
        char emptyChar = ' ';

        StringBuilder sb = new StringBuilder(width);

        double exactFilledUnits = (percentage / 100.0) * width;
        long totalEighthsToFill = Math.round(exactFilledUnits * 8.0);

        for (int i = 0; i < width; i++) {
            if (totalEighthsToFill >= 8) {
                sb.append(blocks[7]);
                totalEighthsToFill -= 8;
            } else if (totalEighthsToFill > 0) {
                sb.append(blocks[(int) totalEighthsToFill - 1]);
                totalEighthsToFill = 0;
            } else {
                sb.append(emptyChar);
            }
        }

        return sb.toString();
    }

    public static void printItem(Item item) {
        printlnColor(item.getName(), Attribute.BOLD());

        if(item instanceof EquipableItem) {
            EquipableItem equipableItem = (EquipableItem) item;
            int durability = equipableItem.getCurrentDurability();
            int maxDurability = equipableItem.getMaxDurability();

            int durabilityPercentage = (durability * 100) / maxDurability;

            Attribute color;

            if (durabilityPercentage < 25) {
                color = Attribute.BRIGHT_RED_TEXT();
            } else if (durabilityPercentage <= 50) {
                color = Attribute.BRIGHT_YELLOW_TEXT();
            } else {
                color = Attribute.BRIGHT_GREEN_TEXT();
            }

            PrintMethods.printColor("Durability ┃", Attribute.CLEAR());
            PrintMethods.printColor(PrintMethods.getProgressBarString(durabilityPercentage, 13), color);
            PrintMethods.printlnColor("┃", Attribute.CLEAR());
        }

        int price = item.getPrice();

        if(price > 0) PrintMethods.printlnColor("Value: "+ price + "G", Attribute.YELLOW_TEXT());

        PrintMethods.printlnColor(item.getDescription(), Attribute.BRIGHT_BLUE_TEXT());
        ImagePrinter.printImage(item.getImagepath());
    }
}
