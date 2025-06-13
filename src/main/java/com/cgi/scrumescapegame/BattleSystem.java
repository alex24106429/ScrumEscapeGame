package com.cgi.scrumescapegame;

import java.util.List;
import java.util.Scanner;

import com.diogonunes.jcolor.Attribute;

import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.graphics.WallpaperHandler;
import com.cgi.scrumescapegame.items.BattleItem;
import com.cgi.scrumescapegame.items.Item;
import com.cgi.scrumescapegame.items.UsableItem;

public class BattleSystem {

    public static void startBattle(Player player, Enemy enemy, Scanner scanner) {
        WallpaperHandler.setWallpaper("boss");
        initializeBattle(player, enemy);

        int totalDamageDealt = 0;
        while (player.isAlive() && enemy.isAlive()) {
            // player’s turn
            int damageThisTurn = playerTurn(player, enemy, scanner);
            totalDamageDealt += damageThisTurn;

            if (!enemy.isAlive()) {
                printEnemyDefeated(enemy);
                break;
            }

            // enemy’s turn
            enemyTurn(player, enemy);
            if (!player.isAlive()) {
                break;
            }

            printHealthStatus(player, enemy);
        }

        concludeBattle(player, totalDamageDealt);
        WallpaperHandler.setWallpaper("dungeon");
    }

    private static void initializeBattle(Player player, Enemy enemy) {
        PrintMethods.clearScreen();
        ImagePrinter.printImage(enemy.getImagePath());
        enemy.setHealth(enemy.getMaxHealth());
        PrintMethods.typeTextColor("\nA wild " + enemy.getName() + " appears!", Attribute.BRIGHT_RED_TEXT());
        System.out.println("HP: " + enemy.getHealth());
        player.printStatus();
    }

    private static int playerTurn(Player player, Enemy enemy, Scanner scanner) {
        while (true) {
            PrintMethods.printlnColor("\n--- Jouw beurt ---", Attribute.BOLD());
            PrintMethods.printlnColor("1. Val aan met " + player.getWeaponName(), Attribute.BRIGHT_CYAN_TEXT());
            PrintMethods.printlnColor("2. Gebruik een item", Attribute.BRIGHT_CYAN_TEXT());
            PrintMethods.printlnColor("Je actie: ", Attribute.BRIGHT_CYAN_TEXT());

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    // attack
                    return handlePlayerAttack(player, enemy);

                case "2":
                    // item
                    boolean back = handleItemUse(player, enemy, scanner);
                    if (back) {
                        // user typed “terug” → re-display action menu
                        continue;
                    } else {
                        // used an item (or invalid item consumed turn)
                        return 0;
                    }

                default:
                    // invalid top-level choice → re-prompt, no enemy turn
                    System.out.println("Ongeldige invoer. Voer een getal in of 'terug'.");
                    continue;
            }
        }
    }

    private static int handlePlayerAttack(Player player, Enemy enemy) {
        float multiplier = QuickTimeEvent.runQuickTimeEvent();
        System.out.println("Je attack damage: " + (int) (multiplier * 200) + "%");
        int finalDamage = Math.round(player.getAttack() * multiplier * 2);
        enemy.takeDamage(finalDamage);
        PrintMethods.typeTextColor(
                "Je valt " + enemy.getName() + " aan, en doet " + finalDamage + " hp damage!",
                Attribute.BRIGHT_GREEN_TEXT());
        return finalDamage;
    }

    /**
     * @return true if the user typed “terug” (go back to action menu);
     *         false if an item was used or invalid input consumed the turn.
     */
    private static boolean handleItemUse(Player player, Enemy enemy, Scanner scanner) {
        List<Item> items = player.getItems();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item instanceof UsableItem || item instanceof BattleItem) {
                System.out.print((i + 1) + ". ");
                PrintMethods.printItem(item);
            }
        }

        PrintMethods.printColor(
                "Voer het nummer in van het item dat je wilt gebruiken (of 'terug' om terug te gaan):",
                Attribute.BRIGHT_BLUE_TEXT());
        String itemInput = scanner.nextLine().trim().toLowerCase();

        if (itemInput.equals("terug")) {
            return true;
        }

        try {
            int num = Integer.parseInt(itemInput);
            int idx = num - 1;
            if (idx >= 0 && idx < items.size()) {
                Item item = items.get(idx);
                if (item instanceof UsableItem) {
                    player.useItem(idx);
                } else if (item instanceof BattleItem) {
                    player.useBattleItem(idx, enemy);
                }
            } else {
                PrintMethods.printlnColor("Ongeldig itemnummer.", Attribute.BRIGHT_RED_TEXT());
            }
        } catch (NumberFormatException e) {
            PrintMethods.printlnColor(
                    "Ongeldige invoer. Voer een getal in of 'terug'.",
                    Attribute.BRIGHT_RED_TEXT());
        }

        // item use (or bad input) consumes the turn
        return false;
    }

    private static void enemyTurn(Player player, Enemy enemy) {
        System.out.println("\n--- " + enemy.getName() + "'s Beurt ---");
        int dmg = enemy.performAttack(player);
        if(dmg != 0) {
            PrintMethods.typeTextColor(
                    enemy.getName() + " gebruikt " + enemy.getLastActionName() +
                            " en doet " + dmg + " hp damage!",
                    Attribute.BRIGHT_RED_TEXT());
        }else{
            PrintMethods.typeTextColor(
                    enemy.getName() + " gebruikt " + enemy.getLastActionName(),
                    Attribute.BRIGHT_RED_TEXT());
        }
        if (!player.isAlive()) {
            PrintMethods.typeTextColor("Je bent verslagen!", Attribute.BRIGHT_RED_TEXT());
        }
    }

    private static void printHealthStatus(Player player, Enemy enemy) {
        System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
        System.out.println(player.getName() + " HP: " + player.getCurrentHp());
    }

    private static void printEnemyDefeated(Enemy enemy) {
        PrintMethods.typeTextColor(enemy.getName() + " is verslagen!", Attribute.BRIGHT_GREEN_TEXT());
    }

    private static void concludeBattle(Player player, int totalDamageDealt) {
        if (player.isAlive()) {
            PrintMethods.printlnColor("\nBattle gewonnen!", Attribute.BRIGHT_GREEN_TEXT());
            player.gainExperience(totalDamageDealt);
        } else {
            PrintMethods.printlnColor("\nBattle verloren!", Attribute.BRIGHT_RED_TEXT());
        }
    }
}