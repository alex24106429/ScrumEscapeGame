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
        PrintMethods.typeTextColor("\neen wilde " + enemy.getName() + " Verschijnt!", Attribute.BRIGHT_RED_TEXT());
        System.out.println("HP: " + enemy.getHealth());
        player.printStatus();

        while (player.isAlive() && enemy.isAlive()) {
            PrintMethods.printlnColor("\n--- Jouw beurt ---", Attribute.BOLD());
            PrintMethods.printlnColor("1. Val aan met " + player.getWeaponName(), Attribute.BRIGHT_CYAN_TEXT());
            PrintMethods.printlnColor("2. Gebruik voorwerp", Attribute.BRIGHT_CYAN_TEXT());
            PrintMethods.printlnColor("Kies je actie: ", Attribute.BRIGHT_CYAN_TEXT());

            String input = scanner.nextLine();

            switch(input) {
                case "1":
                    int playerDamage = player.getAttack();
                    enemy.takeDamage(playerDamage);
                    PrintMethods.typeTextColor("Je hebt " + enemy.getName() + " aangevallen, en deed " + playerDamage + " schade!", Attribute.BRIGHT_GREEN_TEXT());
                    playerDamageDealt += playerDamage;
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
                    
                    PrintMethods.printColor("Voer het nummer in wat je wilt gebruiken (of 'annuleer' om terug te gaan): ", Attribute.BRIGHT_BLUE_TEXT());

                    String itemInput = scanner.nextLine().trim().toLowerCase();

                    if (itemInput.equals("annuleer")) {
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
                            PrintMethods.printlnColor("Ingevoerde nummer ongeldig.", Attribute.BRIGHT_RED_TEXT());
                        }
                    } catch (NumberFormatException e) {
                        PrintMethods.printlnColor("Ongeldige invoer. Gebruik een nummer of typ 'annuleer'.", Attribute.BRIGHT_RED_TEXT());
                    }
                    break;
                default:
                    System.out.println("Ongeldige actie.");
                    continue;
            }

            if (!enemy.isAlive()) {
                PrintMethods.typeTextColor(enemy.getName() + " is verslagen!", Attribute.BRIGHT_GREEN_TEXT());
                break;
            }

            System.out.println("\n--- " + enemy.getName() + "'s beurt ---");
            int monsterDamage = enemy.getAttack();
            PrintMethods.typeTextColor(enemy.getName() + " valt je aan voor " + monsterDamage + " schade!", Attribute.BRIGHT_RED_TEXT());
            player.loseHp(monsterDamage);

            if (!player.isAlive()) {
                PrintMethods.typeTextColor("Je bent verslagen!", Attribute.BRIGHT_RED_TEXT());
                break;
            }

            System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
            System.out.println(player.getName() + " HP: " + player.getCurrentHp());
        }

        if (player.isAlive()) {
            PrintMethods.printlnColor("\nGevecht gewonnen!", Attribute.BRIGHT_GREEN_TEXT());
            player.gainExperience(playerDamageDealt);
        } else {
            PrintMethods.printlnColor("\nGevecht verloren!", Attribute.BRIGHT_RED_TEXT());
        }
    }
}