package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.vragen.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReviewPuzzle extends GeneralPuzzle{
    private static final List<Vraag> vragen = new ArrayList<>();
    static {
        // Vraag 1
        vragen.add(new Matching(
                Arrays.asList("Agile", "Scrum", "Sprint", "Product Owner"),
                Arrays.asList("Een iteratie van 1-4 weken", "Een framework voor Agile ontwikkeling", "Een rol binnen Scrum", "Een mindset"),
                Arrays.asList("1D", "2B", "3A", "4C"),
                "Koppel de Agile termen aan hun correcte definities."
        ));
        // Vraag 2
        vragen.add(new OpenVraag(
                "Wat is de maximale timebox voor een Sprint Review voor een sprint van één maand (in uren)?",
                "4",
                "Het is een uur per week van de sprint."
        ));
        // Vraag 3
        vragen.add(new MeerkeuzeVraag(
                "Wat is het hoofddoel van de Sprint Review?",
                Arrays.asList("Het werk van het team goedkeuren of afkeuren", "Het inspecteren van de product increment en feedback verzamelen", "Een formele presentatie geven aan het management", "De voortgang van de sprint bespreken"),
                1,
                "Het is een informele werksessie, geen statusmeeting. Feedback is cruciaal."
        ));
        // Vraag 4
        vragen.add(new MeerkeuzeVraag(
                "Wie zijn de belangrijkste aanwezigen bij een Sprint Review, naast het Scrum Team?",
                Arrays.asList("Andere Development Teams", "HR en Finance", "Stakeholders", "Niemand, het is een interne meeting"),
                2,
                "Deze groep heeft belang bij het product en hun input is waardevol."
        ));
        // Vraag 5
        vragen.add(new OpenVraag(
                "Hoe heet het belangrijkste artefact dat tijdens de Sprint Review wordt gedemonstreerd en geïnspecteerd?",
                "Increment",
                "Het is de som van al het voltooide werk in de sprint."
        ));
        // Vraag 6
        vragen.add(new MeerkeuzeVraag(
                "Wat is de belangrijkste output van de Sprint Review?",
                Arrays.asList("Een goedgekeurd product", "Een verslag van de meeting", "Een aangepaste Product Backlog", "Een lijst met fouten"),
                2,
                "De feedback van stakeholders leidt vaak tot nieuwe ideeën of aanpassingen in de prioriteiten."
        ));
        // Vraag 7
        vragen.add(new MeerkeuzeVraag(
                "Is de Sprint Review een 'demo'?",
                Arrays.asList("Ja, het is exact hetzelfde", "Nee, een review is meer dan een demo; het is een tweerichtingsgesprek", "Nee, er wordt niets gedemonstreerd", "Ja, maar alleen de Product Owner presenteert"),
                1,
                "Een demo is vaak eenrichtingsverkeer, terwijl een review draait om samenwerking en feedback."
        ));
        // Vraag 8
        vragen.add(new OpenVraag(
                "Wat gebeurt er met Product Backlog Items die aan het einde van de sprint niet 'Done' zijn?",
                "Ze gaan terug op de Product Backlog",
                "Ze worden opnieuw ingeschat en geprioriteerd."
        ));
        // Vraag 9
        vragen.add(new MeerkeuzeVraag(
                "Wie is er over het algemeen verantwoordelijk voor het leiden van de Sprint Review?",
                Arrays.asList("De Scrum Master", "De Product Owner", "Het Development Team", "De belangrijkste stakeholder"),
                1,
                "Deze rol nodigt stakeholders uit en zorgt dat de discussie waarde oplevert voor het product."
        ));
        // Vraag 10
        vragen.add(new Matching(
                Arrays.asList("Product Owner", "Development Team", "Scrum Master", "Stakeholders"),
                Arrays.asList("Demonstreert het werk dat 'Done' is", "Zorgt dat de meeting plaatsvindt en iedereen de regels begrijpt", "Geeft feedback op de increment", "Legt uit welke Product Backlog items 'Done' zijn en welke niet"),
                Arrays.asList("1D", "2A", "3B", "4C"),
                "Koppel de rol aan de typische activiteit tijdens de Sprint Review."
        ));
        Collections.shuffle(vragen);
    }
    public ReviewPuzzle() {
        super(vragen);
    }
}