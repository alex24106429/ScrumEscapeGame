package com.cgi.scrumescapegame.items;

public class Sword extends Weapon {
    private int buff = 0;

    public Sword(int buff) {
        super(15 + buff, 20 + buff, 50 + buff * 2);
        this.buff = buff;
    }

    @Override
    public String getName() {
        if (this.buff > 0) {
            return "Ijzeren Zwaard +" + buff;
        }
        return "Ijzeren Zwaard";
    }

    @Override
    public String getImagepath() {
        return "items/sword.png";
    }

    @Override
    public int getBuff() {
        return this.buff;
    }
}