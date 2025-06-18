package com.cgi.scrumescapegame.tests;

import com.cgi.scrumescapegame.hints.HintProvider;
import com.cgi.scrumescapegame.vragen.MeerkeuzeVraag;

import java.util.Arrays;

public class StubTest {
    public static void main(String[] args) {
        HintProvider stub = new StubHintProvider();
        String result = stub.getHint(new MeerkeuzeVraag(
                "Test vraag",
                Arrays.asList("Antwoord 1", "Antwoord 2", "Antwoord 3"),
                0,
                "Dit is een hint voor de test vraag"
        ));
        assert result.equals("Stub hint: Test hint altijd hetzelfde") : "Stub geeft niet de verwachte hint terug";
        System.out.println("StubTest succesvol!");
    }
}

