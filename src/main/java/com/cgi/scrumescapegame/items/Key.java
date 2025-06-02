package com.cgi.scrumescapegame.items;

public class Key extends Item {
    @Override
    public String getName() {
        return "Key";
    }

    @Override
    public String getDescription() {
        return "Opens a door";
    }

    @Override
    public String getImagepath() {
        return "items/key.png";
    }

    public Key() {
        super(0);
    }
}
