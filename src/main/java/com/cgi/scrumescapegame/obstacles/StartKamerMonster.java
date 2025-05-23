package com.cgi.scrumescapegame.obstacles;

import com.cgi.scrumescapegame.Obstacle;

public class StartKamerMonster implements Obstacle {
    private static final String imagepath = "monsters/reviewmonster.png";

    public StartKamerMonster() {
    }

    public String getImagepath() {
        return imagepath;
    }
}