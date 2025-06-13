package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.vragen.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlanningPuzzle extends Puzzle {
    private static final List<Vraag> vragen = new ArrayList<>();
    static {
        // Vraag 1
        vragen.add(new OpenVraag(
                "Wat is de maximale timebox voor een Sprint Planning meeting voor een sprint van één maand (in uren)?",
                "8",
                "Het is één volledige werkdag."
        ));
        // Vraag 2
        vragen.add(new MeerkeuzeVraag(
                "Wie is er verantwoordelijk voor het opstellen van het Sprint Doel (Sprint Goal)?",
                Arrays.asList("De Product Owner", "Het Development Team", "Het hele Scrum Team", "De Scrum Master"),
                2,
                "Dit is een gezamenlijke inspanning om focus te creëren."
        ));
        // Vraag 3
        vragen.add(new Matching(
                Arrays.asList("Input 1", "Input 2", "Output 1", "Output 2"),
                Arrays.asList("Sprint Backlog", "Product Backlog", "Sprint Goal", "Team Capaciteit"),
                Arrays.asList("1B", "2D", "3A", "4C"),
                "Koppel de inputs en outputs aan de Sprint Planning meeting."
        ));
        // Vraag 4
        vragen.add(new MeerkeuzeVraag(
                "Wie heeft de uiteindelijke beslissing over hoeveel werk er in een Sprint Backlog wordt opgenomen?",
                Arrays.asList("De Scrum Master", "De Product Owner", "De belangrijkste stakeholder", "Het Development Team"),
                3,
                "Het team dat het werk uitvoert, bepaalt hoeveel ze kunnen oppakken."
        ));
        // Vraag 5
        vragen.add(new OpenVraag(
                "Welke bekende schattingstechniek gebruikt vaak een reeks kaarten gebaseerd op de Fibonacci-reeks?",
                "Planning Poker",
                "Het is een vorm van poker."
        ));
        // Vraag 6
        vragen.add(new MeerkeuzeVraag(
                "Wat is de EERSTE vraag die tijdens de Sprint Planning wordt beantwoord?",
                Arrays.asList("Hoe wordt het gekozen werk gedaan?", "Waarom is deze sprint waardevol?", "Wie doet welke taak?", "Hoeveel story points kunnen we aan?"),
                1,
                "Voordat je bepaalt 'wat' en 'hoe', moet je het 'waarom' weten. Dit vormt het Sprint Doel."
        ));
        // Vraag 7
        vragen.add(new MeerkeuzeVraag(
                "Wat is GEEN input voor de Sprint Planning?",
                Arrays.asList("De Product Backlog", "De meest recente 'Done' increment", "De definitieve velocity van de vorige sprint", "De capaciteit van het Development Team"),
                2,
                "Velocity kan een nuttige leidraad zijn, maar het is een resultaat, geen verplichte input."
        ));
        // Vraag 8
        vragen.add(new OpenVraag(
                "Uit welke twee elementen bestaat de Sprint Backlog?",
                "Het Sprint Doel en de geselecteerde Product Backlog Items",
                "Het 'waarom' en het 'wat'."
        ));
        // Vraag 9
        vragen.add(new MeerkeuzeVraag(
                "Wie moet de Product Backlog Items uitleggen tijdens de Sprint Planning?",
                Arrays.asList("Scrum Master", "Development Team", "Product Owner", "Een analist"),
                2,
                "Deze rol is de 'eigenaar' van de Product Backlog."
        ));
        // Vraag 10
        vragen.add(new MeerkeuzeVraag(
                "Is het Sprint Doel (Sprint Goal) verplicht in Scrum?",
                Arrays.asList("Ja, altijd", "Nee, het is optioneel", "Alleen als de Product Owner dat wil", "Alleen voor sprints langer dan 2 weken"),
                0,
                "Zonder een doel, is het geen Sprint, maar slechts een verzameling werk."
        ));
        Collections.shuffle(vragen);
    }
    public PlanningPuzzle() {
        super(vragen);
    }
}