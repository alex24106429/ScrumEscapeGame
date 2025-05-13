package com.cgi.scrumescapegame.obstacles;

import java.util.Arrays;

import com.cgi.scrumescapegame.Obstacle;
import com.cgi.scrumescapegame.vragen.MeerkeuzeVraag;
import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.vragen.OpenVraag;

public class ReviewMonster implements Obstacle {
    public final Puzzle puzzle;
    private static final String imagepath = "reviewmonster.png";

    public ReviewMonster() {
        puzzle = new Puzzle();
        puzzle.addQuestion(new OpenVraag("Dit is een test openvraag", "kaas"));
        puzzle.addQuestion(new MeerkeuzeVraag("Dit is een test vraag voor de review monster.", Arrays.asList("Optie A (correct)", "Optie B", "Optie C", "Optie D"), 0));
    }

    public Puzzle getPuzzle() {
        return this.puzzle;
    }

    public String getImagepath() {
        return imagepath;
    }
}