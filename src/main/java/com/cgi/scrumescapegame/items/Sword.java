package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Weapon;

public class Sword implements Weapon {
    private static final int maxDurability = 10;
    private int currentDurability = 10;

    public String getName() {
        return "Iron Sword";
    }

    public String getDescription() {
        return "+10 ATK";
    }

    public String getImagepath() {
        return "items/sword.png";
    }

    public int getAttackBonus() {
        return 10;
    }

    public void equip(Player player) {
        player.addAttackModifier(getAttackBonus());
    }

    public void unequip(Player player) {
        player.addAttackModifier(-getAttackBonus());
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
