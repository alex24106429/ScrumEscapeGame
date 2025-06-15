package com.cgi.scrumescapegame.graphics;

import java.awt.Color;
import java.io.IOException;

import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.items.Armor;
import com.cgi.scrumescapegame.items.BattleItem;
import com.cgi.scrumescapegame.items.EquipableItem;
import com.cgi.scrumescapegame.items.Item;
import com.cgi.scrumescapegame.items.LimitedUseItem;
import com.cgi.scrumescapegame.items.UsableItem;
import com.cgi.scrumescapegame.items.Weapon;
import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class PrintMethods {
    private static final String ANSI_HIDE_CURSOR = "\u001B[?25l";
    private static final String ANSI_SHOW_CURSOR = "\u001B[?25h";
    private static final String ANSI_CURSOR_HOME = "\u001B[H";
    
    private static final String ANSI_CLEAR = "\033\143";

    public static void hideCursor() {
        System.out.print( ANSI_HIDE_CURSOR );
    }

    public static void showCursor() {
        System.out.print( ANSI_SHOW_CURSOR );
    }

    public static void cursorHome() {
        System.out.print( ANSI_CURSOR_HOME );
    }

    public static void setCursorPosition(int line, int column) {
        System.out.print("\033[" + line + ";" + column + "H");
    }

    public static void moveCursorUp(int lines) {
        System.out.print( "\u001B[" + lines + "A" );
    }

    public static void moveCursorDown(int lines) {
        System.out.print( "\u001B[" + lines + "B" );
    }

    public static void clearScreen() {
        if (Game.debug)
            return;

        System.out.print(ANSI_CLEAR);

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
            Game.pause(30);
        }
        System.out.println();
    }

    public static void typeTextColor(String text, Attribute attribute) {
        typeText(Ansi.colorize(text, attribute));
    }

    public static void typeTextColor(String text, Attribute attribute1, Attribute attribute2) {
        typeText(Ansi.colorize(text, new Attribute[]{attribute1, attribute2}));
    }

    public static void printColor(String text, Attribute attribute) {
        System.out.print(Ansi.colorize(text, attribute));
    }

    public static void printlnColor(String text, Attribute attribute) {
        System.out.println(Ansi.colorize(text, attribute));
    }

    public static void printColor(String text, Attribute attribute1, Attribute attribute2) {
        System.out.print(Ansi.colorize(text, new Attribute[]{attribute1, attribute2}));
    }

    public static void printlnColor(String text, Attribute attribute1, Attribute attribute2) {
        System.out.println(Ansi.colorize(text, new Attribute[]{attribute1, attribute2}));
    }

    public static void printColor(String text, String hexColorString) {
        System.out.print(Ansi.colorize(text, getHexAttribute(hexColorString)));
    }

    public static void printlnColor(String text, String hexColorString) {
        System.out.println(Ansi.colorize(text, getHexAttribute(hexColorString)));
    }

    public static void printColor(String text, Attribute[] attributes) {
        System.out.print(Ansi.colorize(text, attributes));
    }

    public static void printlnColor(String text, Attribute[] attributes) {
        System.out.println(Ansi.colorize(text, attributes));
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

    public static void printBadge(String text, Attribute attribute) {
        printColor(" " + text + " ", attribute, Attribute.BLACK_TEXT());
        System.out.print(" ");
    }

    public static void printItem(Item item) {
        if(item instanceof UsableItem) printBadge("Bruikbaar", Attribute.BRIGHT_GREEN_BACK());
        if(item instanceof Armor) printBadge("Armor", Attribute.BRIGHT_BLUE_BACK());
        if(item instanceof Weapon) printBadge("Wapen", Attribute.BRIGHT_YELLOW_BACK());
        if(item instanceof BattleItem) printBadge("Battle", Attribute.BRIGHT_RED_BACK());
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

            PrintMethods.printColor("Levensduur ┃", Attribute.CLEAR());
            PrintMethods.printColor(PrintMethods.getProgressBarString(durabilityPercentage, 13), color);
            PrintMethods.printColor("┃ ", Attribute.CLEAR());
            PrintMethods.printlnColor(durability + "/" + maxDurability, color);
        }

        if(item instanceof LimitedUseItem) {
            LimitedUseItem usableItem = (LimitedUseItem) item;
            int usesLeft = usableItem.getUsesLeft();
            if(usesLeft != Integer.MAX_VALUE) printlnColor(usesLeft + 1 + " gebruik(en) over", Attribute.DIM());
        }

        int price = item.getCurrentValue();

        if(price > 0) PrintMethods.printlnColor("Prijs: "+ price + "G", Attribute.YELLOW_TEXT());

        PrintMethods.printlnColor(item.getDescription(), Attribute.BRIGHT_BLUE_TEXT());
        ImagePrinter.printImage(item.getImagepath());
    }

    public static Attribute getHSBAttribute(int h, int s, int b) {
        Color color = Color.getHSBColor((float) h / 360f, (float) s / 100f, (float) b / 100f);
        return Attribute.TEXT_COLOR(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static Attribute getHexAttribute(String hex) {
        Color c;
        try {
            c = Color.decode(hex);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hex color: " + hex, e);
        }
        return Attribute.TEXT_COLOR(c.getRed(), c.getGreen(), c.getBlue());
    }

}
