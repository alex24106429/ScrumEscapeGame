package com.cgi.scrumescapegame.tests;
import com.cgi.scrumescapegame.vragen.Vraag;

public class VraagStub implements Vraag {
    private final String vraagtekst;
    private final String correctAntwoord;
    private final String hint;

    public VraagStub(String vraagtekst, String correctAntwoord, String hint) {
        this.vraagtekst = vraagtekst;
        this.correctAntwoord = correctAntwoord;
        this.hint = hint;
    }

    @Override
    public String getTekst() {
        return vraagtekst;
    }

    @Override
    public void toonVraag() {
        System.out.println(vraagtekst);
        System.out.print("> ");
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        return correctAntwoord.equalsIgnoreCase(antwoord.trim());
    }

    @Override
    public String getCorrectAntwoord() {
        return correctAntwoord;
    }

    @Override
    public String getHint() {
        return hint;
    }
}
