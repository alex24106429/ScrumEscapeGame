package com.cgi.scrumescapegame;

import java.util.Scanner;

import com.diogonunes.jcolor.Attribute;

import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;

public class BattleSystem {

    public static void startBattle(Player player, Enemy enemy, Scanner scanner) {
        PrintMethods.clearScreen();
        ImagePrinter.printImage(enemy.getImagePath());
        PrintMethods.typeTextColor("\nA wild " + enemy.getName() + " appears!", Attribute.BRIGHT_RED_TEXT());
        System.out.println("HP: " + enemy.getHealth());
        player.printStatus();

        while (player.isAlive() && enemy.isAlive()) {
            PrintMethods.printlnColor("\n--- Your Turn ---", Attribute.BOLD());
            PrintMethods.printlnColor("1. Attack with " + player.getWeaponName(), Attribute.BRIGHT_CYAN_TEXT());
            PrintMethods.printlnColor("2. Use Item (Not implemented yet)", Attribute.BRIGHT_CYAN_TEXT());
            PrintMethods.printlnColor("Choose your action: ", Attribute.BRIGHT_CYAN_TEXT());

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("1")) {
                int playerDamage = player.getAttack();
                enemy.takeDamage(playerDamage);
                PrintMethods.typeTextColor("You attacked " + enemy.getName() + ", and dealt " + playerDamage + " damage!", Attribute.BRIGHT_GREEN_TEXT());
            } else {
                System.out.println("Invalid action.");
                continue;
            }

            if (!enemy.isAlive()) {
                PrintMethods.typeTextColor(enemy.getName() + " has been defeated!", Attribute.BRIGHT_GREEN_TEXT());
                break;
            }

            System.out.println("\n--- " + enemy.getName() + "'s Turn ---");
            int monsterDamage = enemy.getAttack();
            PrintMethods.typeTextColor(enemy.getName() + " attacked you and dealt " + monsterDamage + " damage!", Attribute.BRIGHT_RED_TEXT());
            player.loseHp(monsterDamage);

            if (!player.isAlive()) {
                PrintMethods.typeTextColor("You have been defeated!", Attribute.BRIGHT_RED_TEXT());
                break;
            }

            System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
            System.out.println(player.getName() + " HP: " + player.getCurrentHp());
        }

        if (player.isAlive()) {
            PrintMethods.printlnColor("\nBattle won!", Attribute.BRIGHT_GREEN_TEXT());
        } else {
            PrintMethods.printlnColor("\nBattle lost!", Attribute.BRIGHT_RED_TEXT());
        }
    }
}