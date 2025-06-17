package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class Bomb extends Item implements BattleItem, LimitedUseItem {
    @Override
    public String getName() {
        return "Mega Bom";
    }

    @Override
    public String getDescription() {
        return "Doet 200 HP schade aan de vijand.";
    }

    @Override
    public String getImagepath() {
        return "items/bomb.png";
    }

    @Override
    public void useBattleItem(Player player, Enemy enemy) {
        PrintMethods.printlnColor("De bom doet 200 HP schade aan " + enemy.getName() + "!", Attribute.BRIGHT_GREEN_TEXT());
        enemy.changeHp(-200);
    }

    @Override
    public int getUsesLeft() {
        return 0;
    }

    public Bomb() {
        super(100);
    }
}