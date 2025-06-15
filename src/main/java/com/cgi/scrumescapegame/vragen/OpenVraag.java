package com.cgi.scrumescapegame.vragen;

import java.util.List;

import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class OpenVraag implements Vraag {
    private final String tekst;
    private final List<String> correctAntwoordTermen;
    private final String hint;

    public OpenVraag(String tekst, List<String> correctAntwoordTermen, String hint) {
        this.tekst = tekst;
        this.correctAntwoordTermen = correctAntwoordTermen;
        this.hint = hint;
    }

    @Override
    public String getTekst() {
        return tekst;
    }

    @Override
    public void toonVraag() {
        PrintMethods.typeTextColor(tekst, Attribute.BRIGHT_YELLOW_TEXT(), Attribute.BOLD());
        PrintMethods.printColor("> ", Attribute.BRIGHT_BLUE_TEXT());
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        for (String term : correctAntwoordTermen) {
            if (antwoord.toLowerCase().contains(term.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getCorrectAntwoord() {
        return "Het correcte antwoord bevat: " + String.join(" of ", correctAntwoordTermen);
    }

    @Override
    public String getHint() {
        return this.hint;
    }
}
