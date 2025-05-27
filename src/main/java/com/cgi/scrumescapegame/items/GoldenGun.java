package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;
import java.util.Random;

public class GoldenGun extends Item implements BattleItem, LimitedUseItem {
    @Override
    public String getName() {
        return "Golden Gun";
    }

    @Override
    public String getDescription() {
        return "A powerful weapon, we think?";
    }

    @Override
    public String getImagepath() {
        return "items/goldengun.png";
    }

    @Override
    public void useBattleItem(Player player, Enemy enemy) {
        Random r = new Random();
        int roll = r.nextInt(10);

        switch (roll) {
            case 9:
                PrintMethods.printlnColor("You shoot the Golden Gun and deal 999 damage to " + enemy.getName() + "!", Attribute.BRIGHT_GREEN_TEXT());
                enemy.takeDamage(999);
                break;

            case 8:
                PrintMethods.printlnColor("The Golden Gun slips out of your hand and fires at you!", Attribute.BRIGHT_RED_TEXT());
                player.loseHp(1000000);
                break;

            default:
                PrintMethods.printlnColor("You try to use the golden gun... turns out it wasn't loaded.", Attribute.BRIGHT_BLUE_TEXT());
        }
    }

    @Override
    public int getUsesLeft() {
        return 0;
    }

    public GoldenGun() {
        super(100);
    }
}