package com.cgi.scrumescapegame.vragen;

import com.cgi.scrumescapegame.Vraag;

public class VraagPrinter {
    public static void toonVraag(Vraag vraag) {
        if (vraag instanceof MeerkeuzeVraag) {
            new MeerkeuzePrinter((MeerkeuzeVraag) vraag).toonVraag();
        } else if (vraag instanceof Matching) {
            new MatchingPrinter((Matching) vraag).toonVraag();
        } else if (vraag instanceof OpenVraag) {
            new OpenPrinter((OpenVraag) vraag).toonVraag();
        } else {
            System.out.println("Onbekende vraag");
        }
    }
}
