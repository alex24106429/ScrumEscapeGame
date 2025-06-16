package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.items.Item;
import com.diogonunes.jcolor.Attribute;

public class SprintSnipper_ItemSnipper implements AttackBehavior {
    public String getName() {
        return "Item Snipper";
    }

    public int attack(Enemy enemy, Player player) {
        int playerItemCount = player.getItems().size();
        if (playerItemCount < 1) {
            PrintMethods.typeTextColor("Sprint Snipper probeerde een item te snippen, maar je hebt geen items!", Attribute.BRIGHT_GREEN_TEXT());
            return 0;
        }
        int itemIndexToSnip = Randomizer.getRandomInt(0, playerItemCount - 1);
        Item itemToSnip = player.getItem(itemIndexToSnip);

        boolean shouldSnipItem = Randomizer.getRandomBoolean();
        if (shouldSnipItem) {
            PrintMethods.typeTextColor("Je " + itemToSnip.getName() + " is gesnipt!", Attribute.BRIGHT_RED_TEXT());
            player.removeItem(itemIndexToSnip);
        } else {
            PrintMethods.typeTextColor("Sprint Snipper probeerde je " + itemToSnip.getName() + " te snippen, maar het is mislukt!", Attribute.BRIGHT_GREEN_TEXT());
        }
        return 0;
    }
}
