package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.vragen.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlanningPuzzle extends GeneralPuzzle {

	private static final List<Vraag> vragen = new ArrayList<>();
	static {
		vragen.add(new MeerkeuzeVraag(
				"Aan wie moet je tijdens de review het (deel)product opleveren?",
				Arrays.asList("Developers", "Product Owner", "De Stakeholders", "Product Owner en de Stakeholders"),
				2,
				"Wie zijn de belangrijkste ontvangers van de productdemonstratie en feedback?"
		));
		Collections.shuffle(vragen);
	}
	public PlanningPuzzle() {
		super(vragen);
	}
}
