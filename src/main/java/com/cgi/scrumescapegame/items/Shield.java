package com.cgi.scrumescapegame.items;

public class Shield extends Armor {
    public Shield() {
        super(10, 10, 50);
    }

    @Override
    public String getName() {
        return "Shield";
    }

    @Override
    public String getImagepath() {
        return "items/shield.png";
    }
}
