package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;

public class HealingPotion extends Item implements UsableItem, LimitedUseItem {
    @Override
    public String getName() {
        return "Geneesdrankje";
    }

    @Override
    public String getDescription() {
        return "+10 HP";
    }

    @Override
    public String getImagepath() {
        return "items/healingpotion.png";
    }

    @Override
    public void useItem(Player player) {
        player.gainHp(10);
    }

    @Override
    public int getUsesLeft() {
        return 0;
    }

    public HealingPotion() {
        super(25);
    }
}