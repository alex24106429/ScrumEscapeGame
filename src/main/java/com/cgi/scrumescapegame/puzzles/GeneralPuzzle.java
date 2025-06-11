package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.Vraag;

import java.util.ArrayList;
import java.util.List;

public class GeneralPuzzle {
    public Puzzle puzzle = new Puzzle();
    protected static final List<Vraag> vragen = new ArrayList<>();
    GeneralPuzzle(){
        puzzle.addQuestion(useQuestion());
    }
    public static Vraag useQuestion() {
        if (vragen.isEmpty()) {
            return null;
        }
        return vragen.removeFirst();
    }
}
