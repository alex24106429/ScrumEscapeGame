package com.cgi.scrumescapegame;

public interface EquipableItem extends Item {
    public void equip(Player player);
    public void unequip(Player player);
    public int getMaxDurability();
    public int getCurrentDurability();
    public void changeDurability(int amount);
}