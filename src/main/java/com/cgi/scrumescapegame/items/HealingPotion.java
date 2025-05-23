package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;

public class HealingPotion extends UsableItem {
    public String getName() {
        return "Healing Potion";
    }

    public String getDescription() {
        return "+10 HP";
    }

    public String getImagepath() {
        return "items/healingpotion.png";
    }

    public void useItem(Player player) {
        player.gainHp(10);
    }

    public int getUsesLeft() {
        return 1;
    }

    public HealingPotion() {
        super(25);
    }
}