package com.cgi.scrumescapegame;

public interface Vraag {
    String getTekst();
    boolean controleerAntwoord(String antwoord);
    String getCorrectAntwoord();
}