package com.cgi.scrumescapegame.items;

public class Shield extends Armor {
    public Shield() {
        super(10, 10, 50);
    }

    @Override
    public String getName() {
        return "Schild";
    }

    @Override
    public String getImagepath() {
        return "items/shield.png";
    }
}
