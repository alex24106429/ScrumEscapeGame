package com.cgi.scrumescapegame.obstacles;

import java.util.Arrays;

import com.cgi.scrumescapegame.Obstacle;
import com.cgi.scrumescapegame.vragen.MeerkeuzeVraag;
import com.cgi.scrumescapegame.Puzzle;

public class StartKamerMonster implements Obstacle {
    public final Puzzle puzzle;
    private static final String imagepath = "reviewmonster.png";

    public StartKamerMonster() {
        puzzle = new Puzzle();
        puzzle.addQuestion(new MeerkeuzeVraag("Dit is een test vraag voor de startkamer monster.", Arrays.asList("Optie A", "Optie B (correct)", "Optie C", "Optie D"), 1));
    }

    public Puzzle getPuzzle() {
        return this.puzzle;
    }

    public String getImagepath() {
        return imagepath;
    }
}