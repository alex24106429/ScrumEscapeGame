package com.cgi.scrumescapegame.items;

public class Sword extends Weapon {
    public Sword() {
        super(10, 10, 50);
    }

    public String getName() {
        return "Iron Sword";
    }

    public String getImagepath() {
        return "items/sword.png";
    }
}