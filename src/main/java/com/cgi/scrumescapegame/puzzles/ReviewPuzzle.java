package com.cgi.scrumescapegame.puzzles;

import java.util.Arrays;

import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.vragen.*;

public class ReviewPuzzle {
    public Puzzle puzzle = new Puzzle();
    public ReviewPuzzle() {
        puzzle.addQuestion(new Matching(
            Arrays.asList("Agile", "Scrum", "Sprint", "Product Owner"),
            Arrays.asList("Een iteratie van 1-4 weken", "Een framework voor Agile ontwikkeling", "Een rol binnen Scrum", "Een mindset"),
            Arrays.asList("1D", "2B", "3A", "4C"), 
            "Koppel de Agile termen aan hun correcte definities."
        ));
    }
}
