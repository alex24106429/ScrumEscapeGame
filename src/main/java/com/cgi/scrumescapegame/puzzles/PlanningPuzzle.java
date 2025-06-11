package com.cgi.scrumescapegame.puzzles;

import java.util.Arrays;
import java.util.Collections;

import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.vragen.*;

public class PlanningPuzzle extends GeneralPuzzle {
	static {
		vragen.add(new MeerkeuzeVraag(
				"Aan wie moet je tijdens de review het (deel)product opleveren?",
				Arrays.asList("Developers", "Product Owner", "De Stakeholders", "Product Owner en de Stakeholders"),
				2,
				"Wie zijn de belangrijkste ontvangers van de productdemonstratie en feedback?"
		));
		Collections.shuffle(vragen);
	}
}
