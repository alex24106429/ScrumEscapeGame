package com.cgi.scrumescapegame;

import java.util.List;
import java.util.Scanner;

import com.diogonunes.jcolor.Attribute;

import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.items.BattleItem;
import com.cgi.scrumescapegame.items.Item;
import com.cgi.scrumescapegame.items.UsableItem;

public class BattleSystem {

    public static void startBattle(Player player, Enemy enemy, Scanner scanner) {
        int playerDamageDealt = 0;
        PrintMethods.clearScreen();
        ImagePrinter.printImage(enemy.getImagePath());
        enemy.setHealth(enemy.getMaxHealth());
        PrintMethods.typeTextColor("\nA wild " + enemy.getName() + " appears!", Attribute.BRIGHT_RED_TEXT());
        System.out.println("HP: " + enemy.getHealth());
        player.printStatus();

        while (player.isAlive() && enemy.isAlive()) {
            PrintMethods.printlnColor("\n--- Jouw beurt ---", Attribute.BOLD());
            PrintMethods.printlnColor("1. Val aan met " + player.getWeaponName(), Attribute.BRIGHT_CYAN_TEXT());
            PrintMethods.printlnColor("2. Gebruik een item", Attribute.BRIGHT_CYAN_TEXT());
            PrintMethods.printlnColor("Je actie: ", Attribute.BRIGHT_CYAN_TEXT());

            String input = scanner.nextLine();

            switch(input) {
                case "1":
                    float multiplier = QuickTimeEvent.runQuickTimeEvent();
					System.out.println("Je attack damage: " + (int) (multiplier * 200) + "%");
                    int finalDamage = Math.round((float) player.getAttack() * multiplier * 2);
                    enemy.takeDamage(finalDamage);
                    PrintMethods.typeTextColor("Je valt " + enemy.getName() + " aan, en doet " + finalDamage + " hp damage!", Attribute.BRIGHT_GREEN_TEXT());
                    playerDamageDealt += finalDamage;
                    break;
                case "2":
                    List<Item> items = player.getItems();
                    for (int i = 0; i< items.size(); i++) {
                        Item item = items.get(i);
                        if (item instanceof UsableItem || item instanceof BattleItem) {
                            System.out.print(String.format("%d. ", i + 1));
                            PrintMethods.printItem(item);
                        }
                    }
                    
                    PrintMethods.printColor("Voer het nummer in van het item dat je wilt gebruiken (of 'terug' om terug te gaan):", Attribute.BRIGHT_BLUE_TEXT());

                    String itemInput = scanner.nextLine().trim().toLowerCase();

                    if (itemInput.equals("terug")) {
                        continue;
                    }

                    try {
                        int itemNumber = Integer.parseInt(itemInput);
                        int itemIndex = itemNumber - 1;

                        Item item = items.get(itemIndex);

                        if (itemIndex >= 0 && itemIndex < items.size()) { 
                            if (item instanceof UsableItem) player.useItem(itemIndex);
                            if (item instanceof BattleItem) player.useBattleItem(itemIndex, enemy);
                        } else {
                            PrintMethods.printlnColor("Ongeldig itemnummer.", Attribute.BRIGHT_RED_TEXT());
                        }
                    } catch (NumberFormatException e) {
                        PrintMethods.printlnColor("Ongeldige invoer. Voer een getal in of 'terug'.", Attribute.BRIGHT_RED_TEXT());
                    }
                    break;
                default:
                    System.out.println("Ongeldige invoer. Voer een getal in of 'terug'.");
                    continue;
            }

            if (!enemy.isAlive()) {
                PrintMethods.typeTextColor(enemy.getName() + " is verslagen!", Attribute.BRIGHT_GREEN_TEXT());
                break;
            }

            System.out.println("\n--- " + enemy.getName() + "'s Beurt ---");
            int monsterDamage = enemy.performAttack(player);
            PrintMethods.typeTextColor(enemy.getName() + " gebruikt " + enemy.getLastActionName() + " and doet " + monsterDamage + " hp damage!", Attribute.BRIGHT_RED_TEXT());

            if (!player.isAlive()) {
                PrintMethods.typeTextColor("Je bent verslagen!", Attribute.BRIGHT_RED_TEXT());
                break;
            }

            System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
            System.out.println(player.getName() + " HP: " + player.getCurrentHp());
        }

        if (player.isAlive()) {
            PrintMethods.printlnColor("\nBattle gewonnen!", Attribute.BRIGHT_GREEN_TEXT());
            player.gainExperience(playerDamageDealt);
        } else {
            PrintMethods.printlnColor("\nBattle verloren!", Attribute.BRIGHT_RED_TEXT());
        }
    }
}