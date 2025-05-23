package com.cgi.scrumescapegame.enemies;

import com.cgi.scrumescapegame.Enemy;

public class ReviewMonster implements Enemy {
    private static final String imagepath = "monsters/reviewmonster.png";

    public ReviewMonster() {
    }

    public String getImagepath() {
        return imagepath;
    }
}