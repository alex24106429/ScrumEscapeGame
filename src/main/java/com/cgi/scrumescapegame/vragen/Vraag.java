package com.cgi.scrumescapegame.vragen;

public interface Vraag {
    String getTekst();
    void toonVraag();
    boolean controleerAntwoord(String antwoord);
    String getCorrectAntwoord();
    String getHint();
}