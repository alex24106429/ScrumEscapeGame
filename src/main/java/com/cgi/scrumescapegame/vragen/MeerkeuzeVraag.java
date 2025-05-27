package com.cgi.scrumescapegame.vragen;

import java.util.List;

import com.cgi.scrumescapegame.Vraag;

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
    }

    @Override
    public String getTekst() {
        return tekst;
    }

    @Override
    public void toonVraag() {
        System.out.println("Meerkeuzevraag: " + tekst);
        for (int i = 0; i < opties.size(); i++) {
            System.out.println((char)('A' + i) + ". " + opties.get(i));
        }
        System.out.print("Uw antwoord (A, B, C, ...): ");
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