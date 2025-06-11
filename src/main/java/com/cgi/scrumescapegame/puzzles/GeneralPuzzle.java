package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.Vraag;

import java.util.List;

public class GeneralPuzzle {
    public Puzzle puzzle = new Puzzle();

    public GeneralPuzzle(List<Vraag> kamerVragen) {
        if (!kamerVragen.isEmpty()) {
            puzzle.addQuestion(kamerVragen.removeFirst());
        }
    }
}

