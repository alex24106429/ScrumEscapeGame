package com.cgi.scrumescapegame;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class PlayerPrinter {

    public static void printStatus(Player player) {
        String output = "";
        output += Ansi.colorize("[ Speler: " + Ansi.colorize(player.getName(), Attribute.BOLD()), Attribute.BRIGHT_CYAN_TEXT()) + " ] ";
        output += Ansi.colorize("[ Locatie: " + Ansi.colorize(player.getCurrentRoom().getName(), Attribute.BOLD()), Attribute.BRIGHT_BLUE_TEXT()) + " ] ";
        output += Ansi.colorize("[ Levens: " + getLivesString(player.getLives()) + " ] ", Attribute.BRIGHT_RED_TEXT());
        output += Ansi.colorize("[ Score: " + Ansi.colorize("" + player.getScore(), Attribute.BOLD()), Attribute.BRIGHT_YELLOW_TEXT()) + " ]\n";
        output += Ansi.colorize("[ ATK: " + player.getAttack() + " ] ", Attribute.BRIGHT_RED_TEXT());
        output += Ansi.colorize("[ DEF: " + player.getDefense() + " ] ", Attribute.GREEN_TEXT()) + "\n";
        output += Ansi.colorize("[ Toegankelijke Kamers: " + RoomRenderer.renderAvailableRooms(player.getCurrentRoom().getAdjacentRooms()) + " ] ", Attribute.BRIGHT_GREEN_TEXT());

        if (Game.debug) {
            var pos = player.getCurrentRoom().getCurrentPosition();
            output += Ansi.colorize("[ Coördinaten: " + pos.x + ", " + pos.y + " ]", Attribute.BRIGHT_CYAN_TEXT());
        }

        System.out.println(output);
    }

    public static void printEquipped(Player player) {
        var weapon = player.getEquipment().getWeapon();
        var armor = player.getEquipment().getArmor();

        if (weapon != null) {
            PrintMethods.printlnColor("Huidig wapen: " + weapon.getName(), Attribute.BOLD());
            PrintMethods.printColor(weapon.getDescription(), Attribute.BRIGHT_BLUE_TEXT());
            PrintMethods.printDurability(weapon);
            ImagePrinter.printImage(weapon.getImagepath());
        }

        if (armor != null) {
            PrintMethods.printlnColor("Huidig armor: " + armor.getName(), Attribute.BOLD());
            PrintMethods.printColor(armor.getDescription(), Attribute.BRIGHT_BLUE_TEXT());
            PrintMethods.printDurability(armor);
            ImagePrinter.printImage(armor.getImagepath());
        }
    }

    public static void printLifeLost(Player player) {
        PrintMethods.printlnColor("Je hebt een leven verloren! Je hebt nog " + player.getLives() + " levens over.", Attribute.BRIGHT_RED_TEXT());
        printStatus(player);
    }

    public static void printGameOver() {
        PrintMethods.printlnColor("Game over! Je hebt geen levens meer.", Attribute.BRIGHT_RED_TEXT());
    }

    public static void printLifeGained(Player player) {
        PrintMethods.printlnColor("Je hebt een leven gewonnen! Je hebt nu " + player.getLives() + " levens.", Attribute.BRIGHT_GREEN_TEXT());
    }

    public static void printMaxLives() {
        PrintMethods.printlnColor("Je hebt al het maximale aantal levens.", Attribute.BRIGHT_GREEN_TEXT());
    }

    private static String getLivesString(int lives) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lives; i++) sb.append("♥ ");
        for (int i = lives; i < 3; i++) sb.append("♡ ");
        return sb.toString().trim();
    }
}
