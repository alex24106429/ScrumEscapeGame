package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.vragen.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DailyStandupPuzzle extends GeneralPuzzle {
	private static final List<Vraag> vragen = new ArrayList<>();

	static {
		vragen.add(new OpenVraag(
				"Wat is het belangrijkste doel van de Sprint Review?",
				"Feedback verzamelen van stakeholders",
				"Denk aan de interactie met degenen die belang hebben bij het product."
		));
		Collections.shuffle(vragen);
	}

	public DailyStandupPuzzle() {
		super(vragen);
	}

}
