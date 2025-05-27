package com.cgi.scrumescapegame.items;

public class GoldSword extends Weapon{
    public GoldSword() {
        super(20, 20, 100);
    }

    @Override
    public String getName() {
        return "Gold Sword";
    }

    @Override
    public String getImagepath() {
        return "items/goldsword.png";
    }
}
