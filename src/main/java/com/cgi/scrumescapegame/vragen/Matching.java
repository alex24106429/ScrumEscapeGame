package com.cgi.scrumescapegame.vragen;

import com.cgi.scrumescapegame.Vraag;

import java.util.List;

public class Matching implements Vraag {
    private String tekst;
    private List<String> optiesL;
    private List<String> optiesR;
    private List<String> correcteAntwoorden;

    public Matching(String tekst, List<String> optiesL, List<String> optiesR, List<String> correcteAntwoorden) {
        this.tekst = tekst;
        this.optiesL = optiesL;
        this.optiesR = optiesR;
        this.correcteAntwoorden = correcteAntwoorden;
    }

    @Override
    public String getTekst() {
        return tekst;
    }

    @Override
    public void toonVraag() {
        System.out.println(tekst);
        for (int i = 0; i < optiesL.size(); i++) {
                System.out.print((char) ('1' + i) + ".      " + optiesL.get(i));
                System.out.println((char) ('A' + i) + ". " + optiesR.get(i));
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
//                System.out.println(aantalGoedeAntwoorden);
            }
        }
        return aantalGoedeAntwoorden == correcteAntwoorden.size();
    }

    @Override
    public String getCorrectAntwoord() {
        return "Correcte antwoorden zijn: " + String.join(", ", correcteAntwoorden);
    }
}


