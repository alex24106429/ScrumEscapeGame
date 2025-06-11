package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.vragen.*;

import java.util.Arrays;
import java.util.Collections;

public class DailyStandupPuzzle extends GeneralPuzzle {
	static {
		vragen.add(new OpenVraag(
				"Wat is het belangrijkste doel van de Sprint Review?",
				"Feedback verzamelen van stakeholders",
				"Denk aan de interactie met degenen die belang hebben bij het product."
		));
		Collections.shuffle(vragen);
	}
}
