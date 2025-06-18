package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;

public abstract class EquipableItem extends Item {
    private int maxDurability;
    private int currentDurability;

    public EquipableItem(int maxDurability, int initialValue) {
        super(initialValue);
        this.maxDurability = maxDurability;
        this.currentDurability = maxDurability;
    }

    public abstract void equip(Player player);
    public abstract void unequip(Player player);
    public abstract int getBuff();

    public int getCurrentDurability() {
        return currentDurability;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public void changeDurability(int amount) {
        this.currentDurability += amount;
        if (this.currentDurability < 0) {
            this.currentDurability = 0;
        } else if (this.currentDurability > this.maxDurability) {
            this.currentDurability = this.maxDurability;
        }
    }

    @Override
    public int getCurrentValue() {
        return (int) (initialValue * ((double) getCurrentDurability() / getMaxDurability()));
    }
}