package com.cgi.scrumescapegame.items;

public class Key extends Item {
    @Override
    public String getName() {
        return "Sleutel";
    }

    @Override
    public String getDescription() {
        return "Opent alle deuren van de kamer.";
    }

    @Override
    public String getImagepath() {
        return "items/key.png";
    }

    public Key() {
        super(0);
    }
}
