package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;

public class Apple extends Item implements UsableItem, LimitedUseItem {
    @Override
    public String getName() {
        return "Appel";
    }

    @Override
    public String getDescription() {
        return "+25 HP";
    }

    @Override
    public String getImagepath() {
        return "items/apple.png";
    }

    @Override
    public void useItem(Player player) {
        player.changeHp(25);
    }

    @Override
    public int getUsesLeft() {
        return 0;
    }

    public Apple() {
        super(10);
    }
}