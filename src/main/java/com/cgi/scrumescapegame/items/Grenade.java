package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class Grenade extends Item implements BattleItem, LimitedUseItem {
    @Override
    public String getName() {
        return "Granaat";
    }

    @Override
    public String getDescription() {
        return "Doet 100 schade aan de vijand.";
    }

    @Override
    public String getImagepath() {
        return "items/grenade.png";
    }

    @Override
    public void useBattleItem(Player player, Enemy enemy) {
        PrintMethods.printlnColor("De granaat doet 100 damage aan " + enemy.getName() + "!", Attribute.BRIGHT_GREEN_TEXT());
        enemy.changeHp(-100);
    }

    @Override
    public int getUsesLeft() {
        return 0;
    }

    public Grenade() {
        super(200);
    }
}