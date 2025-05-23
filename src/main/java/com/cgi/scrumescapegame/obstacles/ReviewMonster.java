package com.cgi.scrumescapegame.obstacles;

import com.cgi.scrumescapegame.Obstacle;

public class ReviewMonster implements Obstacle {
    private static final String imagepath = "monsters/reviewmonster.png";

    public ReviewMonster() {
    }

    public String getImagepath() {
        return imagepath;
    }
}