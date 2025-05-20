package com.cgi.scrumescapegame.vragen;

public class OpenPrinter {
    public void toonVraag(OpenVraag vraag) {
        System.out.println("Openvraag: " + vraag.getTekst());
        System.out.print("Uw antwoord: ");
    }
}
