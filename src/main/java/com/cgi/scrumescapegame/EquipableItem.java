package com.cgi.scrumescapegame;

public interface EquipableItem extends Item {
    public void equip(Player player);
    public void unequip(Player player);
}