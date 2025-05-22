package com.cgi.scrumescapegame.vragen;

public class OpenPrinter {
    private OpenVraag vraag;

    public OpenPrinter(OpenVraag vraag){
        this.vraag = vraag;
    }

    public void toonVraag() {
        System.out.println("Openvraag: " + vraag.getTekst());
        System.out.print("Uw antwoord: ");
    }
}
