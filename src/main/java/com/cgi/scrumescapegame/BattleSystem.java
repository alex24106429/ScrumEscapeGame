package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.items.Weapon;
import com.cgi.scrumescapegame.items.Armor;
import java.util.Scanner;

public class BattleSystem {

    public void startBattle(Player player, Enemy enemy, Scanner scanner) {
        System.out.println("\nA wild " + enemy.getName() + " appears!");
        System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
        System.out.println(player.getName() + " HP: " + player.getHpString());

        while (player.isAlive() && enemy.isAlive()) {

            System.out.println("\n--- Your Turn ---");
            System.out.println("1. Attack");
            System.out.println("2. Use Item (Not implemented in this example)");
            System.out.print("Choose your action: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("1")) {
                int playerDamage = player.getAttack();
                enemy.takeDamage(playerDamage);
                System.out.println("You attacked " + enemy.getName() + " for " + playerDamage + " damage!");
            } else {
                System.out.println("Invalid action. You lose your turn.");
            }

            if (!enemy.isAlive()) {
                System.out.println(enemy.getName() + " has been defeated!");

                break;
            }

            System.out.println("\n--- " + enemy.getName() + "'s Turn ---");
            int monsterDamage = enemy.getAttack();
            player.loseHp(monsterDamage);
            System.out.println(enemy.getName() + " attacked you for " + monsterDamage + " damage!");

            if (!player.isAlive()) {
                System.out.println("You have been defeated!");

                break;
            }

            System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
            System.out.println(player.getName() + " HP: " + player.getCurrentHp());
        }

        if (player.isAlive()) {
            System.out.println("\nBattle won!");
        } else {
            System.out.println("\nBattle lost!");
        }
    }
}