package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.vragen.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RetrospectivePuzzle extends Puzzle {
    private static final List<Vraag> vragen = new ArrayList<>();
    static {
        // Vraag 1
        vragen.add(new OpenVraag(
                "Wat is de maximale timebox voor een Sprint Retrospective voor een sprint van één maand (in uren)?",
                "3",
                "Het is drie kwartier per week van de sprint."
        ));
        // Vraag 2
        vragen.add(new MeerkeuzeVraag(
                "Wat is het hoofddoel van de Sprint Retrospective?",
                Arrays.asList("Het inspecteren van de product increment", "Het plannen van de volgende sprint", "Het verbeteren van het teamproces en de samenwerking", "Het team beoordelen op prestaties"),
                2,
                "De 'Retro' kijkt naar hoe het team werkt, niet naar wat het heeft gemaakt."
        ));
        // Vraag 3
        vragen.add(new MeerkeuzeVraag(
                "Wie neemt deel aan de Sprint Retrospective?",
                Arrays.asList("Het Development Team en de Scrum Master", "Het hele Scrum Team (inclusief Product Owner)", "Alleen het Development Team", "Het Scrum Team en de stakeholders"),
                1,
                "Het hele team, inclusief de Product Owner, moet samenwerken om processen te verbeteren."
        ));
        // Vraag 4
        vragen.add(new OpenVraag(
                "Wat is het belangrijkste resultaat (output) van een Sprint Retrospective?",
                "Een lijst met concrete verbeteracties",
                "Het doel is om niet alleen te praten, maar ook te doen."
        ));
        // Vraag 5
        vragen.add(new Matching(
                Arrays.asList("Start", "Stop", "Continue"),
                Arrays.asList("Met welke dingen moeten we ophouden?", "Met welke dingen moeten we beginnen?", "Welke dingen moeten we blijven doen?"),
                Arrays.asList("1B", "2A", "3C"),
                "Dit is een bekende techniek voor het verzamelen van feedback."
        ));
        // Vraag 6
        vragen.add(new MeerkeuzeVraag(
                "Wie is er verantwoordelijk voor het faciliteren van de Sprint Retrospective?",
                Arrays.asList("De Product Owner", "Een externe facilitator", "Iemand van het Development Team", "De Scrum Master"),
                3,
                "Deze rol dient als de coach van het team en bewaakt de processen."
        ));
        // Vraag 7
        vragen.add(new MeerkeuzeVraag(
                "Waar in de sprint-cyclus vindt de Retrospective plaats?",
                Arrays.asList("Aan het begin van de sprint", "Na de Sprint Planning", "Na de Sprint Review en voor de volgende Sprint Planning", "Wanneer het team het nodig acht"),
                2,
                "Het is het laatste wat je doet voordat je een nieuwe sprint begint."
        ));
        // Vraag 8
        vragen.add(new MeerkeuzeVraag(
                "Waarop ligt de focus tijdens de Retrospective?",
                Arrays.asList("Het product en de features", "Individuele prestaties", "Relaties, processen en tools", "Het budget en de planning"),
                2,
                "De focus ligt op het 'hoe' van het werk, niet op het 'wat'."
        ));
        // Vraag 9
        vragen.add(new OpenVraag(
                "Hoe noem je de regel dat iedereen tijdens een Retrospective open en eerlijk mag zijn zonder angst voor vergelding?",
                "Prime Directive",
                "Het begint met 'Prime'..."
        ));
        // Vraag 10
        vragen.add(new MeerkeuzeVraag(
                "Wat gebeurt er met de verbeteracties uit de Retrospective?",
                Arrays.asList("Ze worden aan het management gerapporteerd", "Ze worden (indien passend) toegevoegd aan de Sprint Backlog van de volgende sprint", "Ze worden opgeslagen voor jaarlijkse evaluatie", "De Scrum Master is alleen verantwoordelijk voor de uitvoering"),
                1,
                "Om ervoor te zorgen dat verbeteringen daadwerkelijk worden doorgevoerd, moeten ze als werk worden behandeld."
        ));
        Collections.shuffle(vragen);
    }
    public RetrospectivePuzzle() {
        super(vragen);
    }
}