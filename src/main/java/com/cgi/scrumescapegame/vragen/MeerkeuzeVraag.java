package com.cgi.scrumescapegame.vragen;

import java.util.List;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class MeerkeuzeVraag implements Vraag {
    private String tekst;
    private List<String> opties;
    private int correcteOptieIndex; 
    private String hint;

    public MeerkeuzeVraag(String tekst, List<String> opties, int correcteOptieIndex, String hint) {
        this.tekst = tekst;
        this.opties = opties;
        if (correcteOptieIndex < 0 || correcteOptieIndex >= opties.size()) {
            throw new IllegalArgumentException("Correcte optie index is ongeldig.");
        }
        this.correcteOptieIndex = correcteOptieIndex;
        this.hint = hint;
    }

    @Override
    public String getTekst() {
        return tekst;
    }

    @Override
    public void toonVraag() {
        PrintMethods.printlnColor(tekst, Attribute.BRIGHT_YELLOW_TEXT(), Attribute.BOLD());

        for (int i = 0; i < opties.size(); i++) {
            PrintMethods.printColor((char)('A' + i) + ". ", Attribute.BOLD());
            System.out.println(opties.get(i));
        }
        PrintMethods.printColor("(A/B/C/D) > ", Attribute.BRIGHT_BLUE_TEXT());
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        if (antwoord == null || antwoord.trim().isEmpty()) {
            return false;
        }
        char gekozenLetter = antwoord.trim().toUpperCase().charAt(0);
        int gekozenIndex = gekozenLetter - 'A';
        return gekozenIndex == correcteOptieIndex;
    }

    @Override
    public String getCorrectAntwoord() {
        return (char)('A' + correcteOptieIndex) + ". " + opties.get(correcteOptieIndex);
    }

    @Override
    public String getHint() {
        return this.hint;
    }
}