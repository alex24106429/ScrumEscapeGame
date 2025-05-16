package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Armor;

public class Shield implements Armor {
    private static final int maxDurability = 10;
    private int currentDurability = 10;

    public String getName() {
        return "Shield";
    }

    public String getDescription() {
        return "+10 DEF";
    }

    public String getImagepath() {
        return "items/shield.png";
    }

    public int getDefenseBonus() {
        return 10;
    }

    public void equip(Player player) {
        player.addDefenseModifier(getDefenseBonus());
    }

    public void unequip(Player player) {
        player.addDefenseModifier(-getDefenseBonus());
    }

    public int getCurrentDurability() {
        return currentDurability;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public void changeDurability(int amount) {
        this.currentDurability += amount;
    }
}
