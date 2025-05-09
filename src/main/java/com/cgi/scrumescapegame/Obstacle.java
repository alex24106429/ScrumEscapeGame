package com.cgi.scrumescapegame;

public interface Obstacle {
    public boolean getIsOvercome();
    public void attempt(Player p);
}
