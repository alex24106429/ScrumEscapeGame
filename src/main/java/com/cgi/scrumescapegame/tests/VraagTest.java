package com.cgi.scrumescapegame.tests;

import com.cgi.scrumescapegame.vragen.Vraag;

public class VraagTest {

    public static void main(String[] args) {
        Vraag vraag = new VraagStub("Kaas is kaas?", "Koekje", "Hints zijn voor losers!");

        // Test: getTekst()
        if ("Kaas is kaas?".equals(vraag.getTekst())) {
            System.out.println("✅ getTekst() werkt correct");
        } else {
            System.out.println("❌ getTekst() geeft verkeerde output");
        }

        // Test: controleerAntwoord() met correct antwoord
        if (vraag.controleerAntwoord("Koekje")) {
            System.out.println("✅ controleerAntwoord() herkent correct antwoord");
        } else {
            System.out.println("❌ controleerAntwoord() herkent correct antwoord NIET");
        }

        // Test: controleerAntwoord() met fout antwoord
        if (!vraag.controleerAntwoord("Je bent een duif")) {
            System.out.println("✅ controleerAntwoord() herkent fout antwoord");
        } else {
            System.out.println("❌ controleerAntwoord() herkent fout antwoord NIET");
        }

        // Test: getHint()
        if ("Hints zijn voor losers!".equals(vraag.getHint())) {
            System.out.println("✅ getHint() werkt correct");
        } else {
            System.out.println("❌ getHint() geeft verkeerde output");
        }

        // Test: getCorrectAntwoord()
        if ("Koekje".equals(vraag.getCorrectAntwoord())) {
            System.out.println("✅ getCorrectAntwoord() werkt correct");
        } else {
            System.out.println("❌ getCorrectAntwoord() geeft verkeerde output");
        }
    }
}
