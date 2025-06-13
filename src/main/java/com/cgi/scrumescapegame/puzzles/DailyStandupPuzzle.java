package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.vragen.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DailyStandupPuzzle extends GeneralPuzzle {
    private static final List<Vraag> vragen = new ArrayList<>();
    static {
        // Vraag 1
        vragen.add(new OpenVraag(
                "Hoeveel minuten is de timebox voor een Daily Standup maximaal?",
                "15",
                "Het is gelijk aan een kwartier."
        ));
        // Vraag 2
        vragen.add(new MeerkeuzeVraag(
                "Wie zijn de verplichte deelnemers aan de Daily Standup?",
                Arrays.asList("De Scrum Master en Product Owner", "Het Development Team", "Het hele Scrum Team", "Het Development Team en de Scrum Master"),
                1,
                "De bijeenkomst is primair bedoeld voor degenen die het werk uitvoeren."
        ));
        // Vraag 3
        vragen.add(new MeerkeuzeVraag(
                "Wat is het hoofddoel van de Daily Standup?",
                Arrays.asList("De Product Owner een statusupdate geven", "Werk verdelen voor de dag", "Het plan voor de komende 24 uur inspecteren en aanpassen", "Problemen met het management bespreken"),
                2,
                "Het is een planningsmoment voor het team, niet een rapportagemoment."
        ));
        // Vraag 4
        vragen.add(new OpenVraag(
                "Wat is een andere, meer formele naam voor de Daily Standup?",
                "Daily Scrum",
                "Het staat in de Scrum Gids."
        ));
        // Vraag 5
        vragen.add(new MeerkeuzeVraag(
                "Welke van de volgende vragen is GEEN standaardvraag in de Daily Standup?",
                Arrays.asList("Wat heb ik gisteren gedaan?", "Wat ga ik vandaag doen?", "Wanneer is de feature af?", "Zie ik belemmeringen (impediments)?"),
                2,
                "De vragen focussen op het plan en de voortgang, niet op deadlines van individuele features."
        ));
        // Vraag 6
        vragen.add(new MeerkeuzeVraag(
                "Wat gebeurt er als een teamlid een belemmering (impediment) identificeert tijdens de Daily Standup?",
                Arrays.asList("Het team stopt en lost het probleem direct op", "De Scrum Master noteert het om na de meeting op te pakken", "De Product Owner moet het oplossen", "Het wordt genegeerd tot de Retrospective"),
                1,
                "De Daily Standup zelf is te kort om problemen op te lossen, het is voor signalering."
        ));
        // Vraag 7
        vragen.add(new Matching(
                Arrays.asList("Gisteren", "Vandaag", "Belemmeringen"),
                Arrays.asList("Wat ga ik doen om het Sprint Doel te helpen behalen?", "Wat heb ik gedaan om het Sprint Doel te helpen behalen?", "Wat staat mij of het team in de weg?"),
                Arrays.asList("1B", "2A", "3C"),
                "Koppel de drie klassieke vragen aan hun focus."
        ));
        // Vraag 8
        vragen.add(new MeerkeuzeVraag(
                "Wie is er verantwoordelijk voor dat de Daily Standup plaatsvindt?",
                Arrays.asList("De Product Owner", "De Project Manager", "Het Development Team", "De Scrum Master"),
                3,
                "Deze rol coacht het team in het correct uitvoeren van Scrum events."
        ));
        // Vraag 9
        vragen.add(new OpenVraag(
                "Als de Daily Standup langer duurt dan de timebox, wie grijpt dan in?",
                "Scrum Master",
                "Deze rol bewaakt het Scrum proces."
        ));
        // Vraag 10
        vragen.add(new MeerkeuzeVraag(
                "Waarom wordt de Daily Standup elke dag op dezelfde tijd en plaats gehouden?",
                Arrays.asList("Om het makkelijk te maken voor stakeholders om aan te sluiten", "Het reduceert complexiteit en overhead", "Omdat de agenda's van het team vol zijn", "Het is geen vaste regel"),
                1,
                "Consistentie helpt bij het vormen van een routine."
        ));
        Collections.shuffle(vragen);
    }
    public DailyStandupPuzzle() {
        super(vragen);
    }
}