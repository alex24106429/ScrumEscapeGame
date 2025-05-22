package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;

public interface UsableItem extends Item {
    public void useItem(Player player);
    public int getUsesLeft();
}
