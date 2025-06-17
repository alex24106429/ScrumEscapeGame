package com.cgi.scrumescapegame.items;

public class GoldSword extends Weapon {
    private int buff = 0;

    public GoldSword(int buff) {
        super(30 + buff, 40 + buff, 100 + buff * 2);
        this.buff = buff;
    }

    @Override
    public String getName() {
        if (this.buff > 0) {
            return "Gouden Zwaard +" + buff;
        }
        return "Gouden Zwaard";
    }

    @Override
    public String getImagepath() {
        return "items/goldsword.png";
    }
}
