package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;

public abstract class Armor extends EquipableItem {
    private int defenseBonus;

    public Armor(int maxDurability, int defenseBonus, int initialValue) {
        super(maxDurability, initialValue);
        this.defenseBonus = defenseBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    public void equip(Player player) {
        player.addDefenseModifier(getDefenseBonus());
    }

    public void unequip(Player player) {
        player.addDefenseModifier(-getDefenseBonus());
    }

    @Override
    public String getDescription() {
        return "+" + getDefenseBonus() + " DEF";
    }
}