package com.cgi.scrumescapegame;

public interface Obstacle {
    public boolean getIsOvercome();
    void setIsOvercome(boolean isOvercome);
    public void attempt(Player p);
    String getVraag();
    String getImagepath();
}
