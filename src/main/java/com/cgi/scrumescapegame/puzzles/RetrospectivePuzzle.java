package com.cgi.scrumescapegame.puzzles;

import java.util.Arrays;
import java.util.Collections;

import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.vragen.*;

public class RetrospectivePuzzle extends GeneralPuzzle{
	static {
		vragen.add(new MeerkeuzeVraag(
				"Welke van de onderstaande stellingen zijn juist?\n" +
						"I. De Sprint review moet formeel blijven.\n" +
						"II. Alleen items die volgens de ‘Definition of Done’ klaar zijn mogen gedemonstreerd worden tijdens de Sprint Review.\n",
				Arrays.asList("I is juist, II is onjuist", "I is onjuist, II is juist", "I en II zijn juist", "I en II zijn onjuist"),
				1,
				"Denk aan de sfeer van de meeting en de criteria voor demonstratie."
		));
		Collections.shuffle(vragen);
	}
}
