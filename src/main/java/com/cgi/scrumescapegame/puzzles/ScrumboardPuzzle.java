package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.vragen.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScrumboardPuzzle extends GeneralPuzzle {
    private static final List<Vraag> vragen = new ArrayList<>();
    static {
        // Vraag 1
        vragen.add(new Matching(
                Arrays.asList("Kolom 1", "Kolom 2", "Kolom 3"),
                Arrays.asList("In Progress", "To Do", "Done"),
                Arrays.asList("1B", "2A", "3C"),
                "Zet de drie basiskolommen van een scrumbord in de juiste, logische volgorde."
        ));
        // Vraag 2
        vragen.add(new OpenVraag(
                "Waar staat de afkorting 'WIP' voor op een scrumbord?",
                "Work In Progress",
                "Het refereert aan al het werk dat onderhanden is."
        ));
        // Vraag 3
        vragen.add(new MeerkeuzeVraag(
                "Wat is het hoofddoel van het instellen van een 'WIP Limit'?",
                Arrays.asList("Het team langzamer laten werken", "Zoveel mogelijk taken tegelijkertijd oppakken", "Bottlenecks voorkomen en focus bevorderen", "De Scrum Master meer controle geven"),
                2,
                "Door minder tegelijk te doen, wordt werk sneller afgemaakt ('Stop starting, start finishing')."
        ));
        // Vraag 4
        vragen.add(new MeerkeuzeVraag(
                "Wie is primair eigenaar van het scrumbord tijdens de sprint?",
                Arrays.asList("De Product Owner", "Het management", "Het Development Team", "De Scrum Master"),
                2,
                "Het bord is een hulpmiddel voor het team dat het werk uitvoert om hun eigen werk te beheren."
        ));
        // Vraag 5
        vragen.add(new OpenVraag(
                "Hoe heet een item op het scrumbord dat de voortgang van een ander item blokkeert?",
                "Impediment",
                "Een ander woord is belemmering of 'blocker'."
        ));
        // Vraag 6
        vragen.add(new MeerkeuzeVraag(
                "Wat vertegenwoordigt een kaartje (of sticky note) dat op het bord van 'In Progress' naar 'To Do' wordt verplaatst?",
                Arrays.asList("Normale voortgang", "Het werk is voltooid", "Er is een probleem of het werk moet opnieuw worden gedaan", "De prioriteit is verhoogd"),
                2,
                "Beweging van rechts naar links op een bord is meestal een slecht teken."
        ));
        // Vraag 7
        vragen.add(new Matching(
                Arrays.asList("User Story", "Taak (Task)", "Epic", "Spike"),
                Arrays.asList("Een grote brok werk die uit meerdere stories bestaat", "Een activiteit die nodig is om een User Story af te ronden", "Een onderzoekstaak om kennis op te doen", "Een korte beschrijving van een feature vanuit het perspectief van de eindgebruiker"),
                Arrays.asList("1D", "2B", "3A", "4C"),
                "Koppel de term voor een werk-item aan de juiste beschrijving."
        ));
        // Vraag 8
        vragen.add(new MeerkeuzeVraag(
                "Een Scrumbord is hetzelfde als een Product Backlog.",
                Arrays.asList("Waar", "Niet waar"),
                1,
                "Het Scrumbord visualiseert de Sprint Backlog (het werk voor de huidige sprint), niet de gehele Product Backlog."
        ));
        // Vraag 9
        vragen.add(new OpenVraag(
                "Hoe heet een grafiek die de voortgang van het resterende werk in een sprint over tijd laat zien?",
                "Burndown Chart",
                "De lijn in de grafiek zou idealiter naar beneden moeten gaan."
        ));
        // Vraag 10
        vragen.add(new MeerkeuzeVraag(
                "Wat is een 'Swimlane' op een Scrumbord?",
                Arrays.asList("Een verticale kolom voor een teamlid", "Een horizontale baan om werk te groeperen (bv. per feature of prioriteit)", "De ruimte waar afgeronde taken worden geplaatst", "Een timer die de duur van de stand-up bijhoudt"),
                1,
                "Denk aan de banen in een zwembad; ze scheiden verschillende stromen."
        ));
        Collections.shuffle(vragen);
    }
    public ScrumboardPuzzle() {
        super(vragen);
    }
}