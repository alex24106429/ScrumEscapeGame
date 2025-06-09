package com.cgi.scrumescapegame.puzzles;

import java.util.Arrays;

import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.vragen.*;

public class ScrumboardPuzzle {
    public Puzzle puzzle = new Puzzle();
    public ScrumboardPuzzle() {
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
