package com.cgi.scrumescapegame.puzzles;

import java.util.Arrays;

import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.vragen.*;

public class ReviewPuzzle {
    public Puzzle puzzle = new Puzzle();
    public ReviewPuzzle() {
        puzzle.addQuestion(new Matching(
        Arrays.asList("Agile", "Scrum", "Sprint", "Product Owner"),
        Arrays.asList("Een iteratie van 1-4 weken", "Een framework voor Agile ontwikkeling", "Een rol binnen Scrum", "Een mindset"),
        Arrays.asList("1D", "2B", "3A", "4C"), 
        "Koppel de Agile termen aan hun correcte definities."
    ));

    puzzle.addQuestion(new OpenVraag(
        "Wat is het belangrijkste doel van de Sprint Review?",
        "Feedback verzamelen van stakeholders",
        "Denk aan de interactie met degenen die belang hebben bij het product."
    ));

    puzzle.addQuestion(new MeerkeuzeVraag(
        "Aan wie moet je tijdens de review het (deel)product opleveren?",
        Arrays.asList("Developers", "Product Owner", "De Stakeholders", "Product Owner en de Stakeholders"),
        3, 
        "Wie zijn de belangrijkste ontvangers van de productdemonstratie en feedback?"
    ));

    puzzle.addQuestion(new MeerkeuzeVraag(
        "Welke van de onderstaande stellingen zijn juist?\n" +
        "I. De Sprint review moet formeel blijven.\n" +
        "II. Alleen items die volgens de ‘Definition of Done’ klaar zijn mogen gedemonstreerd worden tijdens de Sprint Review.\n",
        Arrays.asList("I is juist, II is onjuist", "I is onjuist, II is juist", "I en II zijn juist", "I en II zijn onjuist"),
        1, 
        "Denk aan de sfeer van de meeting en de criteria voor demonstratie."
    ));

    puzzle.addQuestion(new Matching(
        Arrays.asList(
            "Deze persoon nodigt de Stakeholders uit, vertelt wat het team gedaan heeft en bespreekt na de demonstratie wat de toekomstige stappen zijn.",
            "Deze persoon zorgt ervoor dat de meeting binnen de afgesproken time-box plaatsvindt.",
            "Tijdens de Sprint review bespreekt deze persoon hoe het werk verricht is, wat voor problemen zijn ontstaan, hoe uitdagingen opgelost zijn en welke keuzes er zijn gemaakt."
        ),
        Arrays.asList("Developer", "Product Owner", "Scrum Master"), 
        Arrays.asList("1B", "2C", "3A"),
        "Koppel de verantwoordelijkheden tijdens de Sprint Review aan de juiste Scrum rollen."
    ));
    }
}
