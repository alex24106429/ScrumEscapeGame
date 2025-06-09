package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.vragen.*;

public class DailyStandupPuzzle {
    public Puzzle puzzle = new Puzzle();
    public DailyStandupPuzzle() {
		puzzle.addQuestion(new OpenVraag(
			"Wat is het belangrijkste doel van de Sprint Review?",
			"Feedback verzamelen van stakeholders",
			"Denk aan de interactie met degenen die belang hebben bij het product."
		));
    }
}
