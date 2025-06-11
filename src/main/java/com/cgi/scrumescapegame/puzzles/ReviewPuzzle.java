package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.vragen.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReviewPuzzle extends GeneralPuzzle{
    private static final List<Vraag> vragen = new ArrayList<>();
    static {
        vragen.add(new Matching(
                Arrays.asList("Agile", "Scrum", "Sprint", "Product Owner"),
                Arrays.asList("Een iteratie van 1-4 weken", "Een framework voor Agile ontwikkeling", "Een rol binnen Scrum", "Een mindset"),
                Arrays.asList("1D", "2B", "3A", "4C"),
                "Koppel de Agile termen aan hun correcte definities."
        ));
        Collections.shuffle(vragen);
    }
    public ReviewPuzzle() {
        super(vragen);
    }
}
