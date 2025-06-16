package com.cgi.scrumescapegame.items;

public class GoldSword extends Weapon{
    public GoldSword() {
        super(30, 40, 100);
    }

    @Override
    public String getName() {
        return "Goud Zwaard";
    }

    @Override
    public String getImagepath() {
        return "items/goldsword.png";
    }
}
