package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;

public abstract class Weapon extends EquipableItem {
    private int attackBonus;

    public Weapon(int maxDurability, int attackBonus, int initialValue) {
        super(maxDurability, initialValue);
        this.attackBonus = attackBonus;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public String getDescription() {
        return "+" + getAttackBonus() + " ATK";
    }

    public void equip(Player player) {
        player.addAttackModifier(getAttackBonus());
    }

    public void unequip(Player player) {
        player.addAttackModifier(-getAttackBonus());
    }
}
