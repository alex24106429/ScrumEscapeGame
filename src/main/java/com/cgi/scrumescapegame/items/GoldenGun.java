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
        return "A powerfull weapon, we think?";
    }

    @Override
    public String getImagepath() {
        return "items/GoldenGun.png";
    }

    @Override
    public void useBattleItem(Player player, Enemy enemy) {
        Random r= new Random();
        int r1 = r.nextInt(10);
        if (r1 == 9){
            PrintMethods.printlnColor("You Shoot the Golden Gun and deal 999 damage to " + enemy.getName() + "!", Attribute.BRIGHT_GREEN_TEXT());
            enemy.takeDamage(999);
        } else if(r1 == 8){
            PrintMethods.printlnColor("The Gun Slips out of your hand and fires at you!", Attribute.BRIGHT_GREEN_TEXT());
            player.loseHp(1000000);
        } else {
            PrintMethods.printlnColor("You try to use the golden gun... , Turns out it wasn't loaded.", Attribute.BRIGHT_GREEN_TEXT());
            enemy.takeDamage(0);
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