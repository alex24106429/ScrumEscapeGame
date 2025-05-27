package com.cgi.scrumescapegame.vragen;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

import java.util.List;

public class Matching implements Vraag {
    private List<String> optiesL;
    private List<String> optiesR;
    private List<String> correcteAntwoorden;
    private String hint;

    public Matching(List<String> optiesL, List<String> optiesR, List<String> correcteAntwoorden, String hint) {
        this.optiesL = optiesL;
        this.optiesR = optiesR;
        this.correcteAntwoorden = correcteAntwoorden;
        this.hint = hint;
    }

    @Override
    public String getTekst() {
        return "Match de volgende termen:";
    }

    @Override
    public void toonVraag() {
        System.out.println("Match de volgende termen:");
        for (int i = 0; i < optiesL.size(); i++) {
            if(optiesL.get(i).length() > 40) {
                PrintMethods.printlnColor(i + 1 + ". " + optiesL.get(i), Attribute.BRIGHT_CYAN_TEXT());
            } else {
                PrintMethods.printColor(i + 1 + ". " + String.format("%-40s", optiesL.get(i)), Attribute.BRIGHT_CYAN_TEXT());
            }
            PrintMethods.printlnColor((char) ('A' + i) + ". " + optiesR.get(i), Attribute.BRIGHT_GREEN_TEXT());
        }
        System.out.print("Uw antwoord (1A, 2B, 3C etc...): ");
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

    @Override
    public String getHint() {
        return this.hint;
    }
}


