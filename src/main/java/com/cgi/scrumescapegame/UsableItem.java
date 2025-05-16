package com.cgi.scrumescapegame;

public interface UsableItem extends Item {
    public void useItem(Player player);
    public int getUsesLeft();
}
