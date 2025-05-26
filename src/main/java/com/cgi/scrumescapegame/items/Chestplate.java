package com.cgi.scrumescapegame.items;

public class Chestplate extends Armor{
    public Chestplate() {
        super(20, 20, 100);
    }

    @Override
    public String getName() {
        return "Chestplate";
    }

    @Override
    public String getImagepath() {
        return "items/chestplate.png";
    }
}
