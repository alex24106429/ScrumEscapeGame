package com.cgi.scrumescapegame.items;

public class Shield extends Armor {
    private int buff = 0;

    public Shield(int buff) {
        super(10 + buff, 5 + buff, 50 + buff * 2);
        this.buff = buff;
    }

    @Override
    public String getName() {
        if (this.buff > 0) {
            return "Schild +" + buff;
        }
        return "Schild";
    }

    @Override
    public String getImagepath() {
        return "items/shield.png";
    }
}
