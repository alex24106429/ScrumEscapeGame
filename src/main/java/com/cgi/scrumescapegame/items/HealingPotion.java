package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;

public class HealingPotion extends UsableItem {
    public String getName() {
        return "Healing Potion";
    }

    public String getDescription() {
        return "+1 Leven";
    }

    public String getImagepath() {
        return "items/healingpotion.png";
    }

    public void useItem(Player player) {
        player.gainLife();
    }

    public int getUsesLeft() {
        return 1;
    }

    public HealingPotion() {
        super(25);
    }
}