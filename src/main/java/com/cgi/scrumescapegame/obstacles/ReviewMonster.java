package com.cgi.scrumescapegame.obstacles;

import java.util.Arrays;

import com.cgi.scrumescapegame.Obstacle;
import com.cgi.scrumescapegame.vragen.Matching;
import com.cgi.scrumescapegame.vragen.MeerkeuzeVraag;
import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.vragen.OpenVraag;

public class ReviewMonster implements Obstacle {
    public final Puzzle puzzle;
    private static final String imagepath = "reviewmonster.png";

    public ReviewMonster() {
        puzzle = new Puzzle();
        puzzle.addQuestion(new Matching("Match deze begrippen", Arrays.asList("Agile", "Scrum", "Sprint", "Product Owner"), Arrays.asList("Een iteratie van 1-4 weken", "Een framework voor Agile ontwikkeling", "Een rol binnen Scrum", "Een mindset"), Arrays.asList("1A", "2B", "3C", "4D")));
        puzzle.addQuestion(new OpenVraag("Wat is het belangrijkste doel van de Sprint Review?", "Feedback verzamelen van stakeholders"));
        puzzle.addQuestion(new MeerkeuzeVraag("Aan wie moet je tijdens de review het (deel)product opleveren?", Arrays.asList("Developers", "Product Owner", "De Stakeholders", "Product Owener en de Stakeholders"), 2));
        puzzle.addQuestion(new MeerkeuzeVraag("Welke van de onderstaande stellingen zijn juist?\n" + "I. De Sprint review moet formeel blijven.\n" + "II. Alleen items die volgens de ‘Defintion of Done’ klaar zijn mogen gedemonstreerd worden tijdens de Sprint Review.\n", Arrays.asList("I is juist, II is onjuist", "I is onjuist, II is juist", "I en II zijn juist", "I en II zijn onjuist"), 1));
        puzzle.addQuestion(new Matching("Verbind iedere taak met de juiste persoon", Arrays.asList("Deze persoon nodigt de Stakeholders uit, verteld wat het team gedaan heeft en bespreekt na de demonstratie wat de toekomstige stappen zijn.", "Deze persoon zorgt ervoor dat de meeting binnen de afgesproken time-box plaatsvindt.", "Tijdens de Sprint review bespreekt deze persoon hoe het werk verricht is, wat voor problemen zijn ontstaan, hoe uitdagingen opgelost zijn en welke keuzes er zijn gemaakt."), Arrays.asList("Developer", "Product Owner ", "Scrum Master "), Arrays.asList("1B", "2C", "3A")));


    }

    public Puzzle getPuzzle() {
        return this.puzzle;
    }

    public String getImagepath() {
        return imagepath;
    }
}