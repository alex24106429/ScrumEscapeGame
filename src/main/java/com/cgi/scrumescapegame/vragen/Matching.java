package com.cgi.scrumescapegame.vragen;

import com.cgi.scrumescapegame.PrintMethods;
import com.cgi.scrumescapegame.Vraag;
import com.diogonunes.jcolor.Attribute;

import java.util.List;

public class Matching implements Vraag {
    private List<String> optiesL;
    private List<String> optiesR;
    private List<String> correcteAntwoorden;

    public Matching(List<String> optiesL, List<String> optiesR, List<String> correcteAntwoorden) {
        this.optiesL = optiesL;
        this.optiesR = optiesR;
        this.correcteAntwoorden = correcteAntwoorden;
    }

    @Override
    public String getTekst() {
        return "Match de volgende termen:";
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        if (antwoord == null || antwoord.trim().isEmpty()) {
            return false;
        }
        int aantalGoedeAntwoorden = 0;
        for (int i = 0; i < correcteAntwoorden.size(); i++) {
            if (antwoord.toLowerCase().contains(correcteAntwoorden.get(i).toLowerCase())) {
                aantalGoedeAntwoorden++;
                System.out.println(aantalGoedeAntwoorden);
            }
        }
        return aantalGoedeAntwoorden == correcteAntwoorden.size();
    }



    @Override
    public String getCorrectAntwoord() {
        return "Correcte antwoorden zijn: " + String.join(", ", correcteAntwoorden);
    }

    public List<String> getOptiesL() {
        return optiesL;
    }

    public List<String> getOptiesR() {
        return optiesR;
    }
}


