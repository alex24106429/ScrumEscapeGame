package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class DamagePotion extends Item implements BattleItem, LimitedUseItem {
    @Override
    public String getName() {
        return "Damage Potion";
    }

    @Override
    public String getDescription() {
        return "Deals 25 damage to the enemy.";
    }

    @Override
    public String getImagepath() {
        return "items/damagepotion.png";
    }

    @Override
    public void useBattleItem(Player player, Enemy enemy) {
        PrintMethods.printlnColor("The Damage Potion dealt 25 damage to " + enemy.getName() + "!", Attribute.BRIGHT_GREEN_TEXT());
        enemy.takeDamage(25);
    }

    @Override
    public int getUsesLeft() {
        return 0;
    }

    public DamagePotion() {
        super(50);
    }
}