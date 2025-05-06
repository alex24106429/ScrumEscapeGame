package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Player;

public interface Obstacle {
    public boolean getIsOvercome();
    public void attempt(Player p);
}
