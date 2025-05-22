package com.cgi.scrumescapegame.vragen;

import com.cgi.scrumescapegame.PrintMethods;
import com.diogonunes.jcolor.Attribute;

import java.util.List;

public class MatchingPrinter {
    private Matching vraag;

    public MatchingPrinter(Matching vraag) {
        this.vraag = vraag;
    }

    public void toonVraag() {
        System.out.println("Match de volgende termen:");

        List<String> optiesL = vraag.getOptiesL();
        List<String> optiesR = vraag.getOptiesR();

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
}
