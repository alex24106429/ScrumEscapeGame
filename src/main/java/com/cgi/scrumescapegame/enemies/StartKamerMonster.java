package com.cgi.scrumescapegame.enemies;

import com.cgi.scrumescapegame.Enemy;

public class StartKamerMonster implements Enemy {
    private static final String imagepath = "monsters/reviewmonster.png";

    public StartKamerMonster() {
    }

    public String getImagepath() {
        return imagepath;
    }
}