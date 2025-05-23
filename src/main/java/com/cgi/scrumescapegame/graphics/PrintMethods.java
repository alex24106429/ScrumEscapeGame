package com.cgi.scrumescapegame.graphics;

import java.io.IOException;

import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.items.EquipableItem;
import com.cgi.scrumescapegame.items.Item;
import com.cgi.scrumescapegame.items.UsableItem;
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
        if(Game.debug) {
            System.out.println(text);
            return;
        }
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(30);
            } catch (InterruptedException ignored) {}
        }
        System.out.println();
    }

    public static void typeTextColor(String text, Attribute colorAttribute) {
        typeText(Ansi.colorize(text, colorAttribute));
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
            PrintMethods.printColor("┃ ", Attribute.CLEAR());
            PrintMethods.printlnColor(durability + "/" + maxDurability, color);
        }

        if(item instanceof UsableItem) {
            UsableItem usableItem = (UsableItem) item;
            int usesLeft = usableItem.getUsesLeft();
            if(usesLeft != Integer.MAX_VALUE) printlnColor(usesLeft + " use(s) left", Attribute.DIM());
        }

        int price = item.getCurrentValue();

        if(price > 0) PrintMethods.printlnColor("Value: "+ price + "G", Attribute.YELLOW_TEXT());

        PrintMethods.printlnColor(item.getDescription(), Attribute.BRIGHT_BLUE_TEXT());
        ImagePrinter.printImage(item.getImagepath());
    }
}
