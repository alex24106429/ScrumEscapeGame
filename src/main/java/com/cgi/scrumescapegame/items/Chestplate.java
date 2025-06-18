package com.cgi.scrumescapegame.items;

public class Chestplate extends Armor {
    private int buff = 0;

    public Chestplate(int buff) {
        super(20 + buff, 10 + buff, 100 + buff * 2);
        this.buff = buff;
    }

    @Override
    public String getName() {
        if (this.buff > 0) {
            return "Harnas +" + buff;
        }
        return "Harnas";
    }

    @Override
    public String getImagepath() {
        return "items/chestplate.png";
    }

    @Override
    public int getBuff() {
        return this.buff;
    }
}