package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class DamagePotion extends Item implements BattleItem, LimitedUseItem {
    @Override
    public String getName() {
        return "Explosie Drankje";
    }

    @Override
    public String getDescription() {
        return "Deze drank doet 50 schade aan de vijand.";
    }

    @Override
    public String getImagepath() {
        return "items/damagepotion.png";
    }

    @Override
    public void useBattleItem(Player player, Enemy enemy) {
        PrintMethods.printlnColor("Het explosie drankje doet 50 schade aan " + enemy.getName() + "!", Attribute.BRIGHT_GREEN_TEXT());
        enemy.changeHp(-50);
    }

    @Override
    public int getUsesLeft() {
        return 0;
    }

    public DamagePotion() {
        super(50);
    }
}