package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;

public abstract class UsableItem extends Item {
    public abstract void useItem(Player player);
    public abstract int getUsesLeft();

    public UsableItem(int initialValue) {
        super(initialValue);
    }
}