package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.vragen.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RetrospectivePuzzle extends GeneralPuzzle{

	private static final List<Vraag> vragen = new ArrayList<>();
	static {
		vragen.add(new MeerkeuzeVraag(
                """
                        Welke van de onderstaande stellingen zijn juist?
                        I. De Sprint review moet formeel blijven.
                        II. Alleen items die volgens de ‘Definition of Done’ klaar zijn mogen gedemonstreerd worden tijdens de Sprint Review.
                        """,
				Arrays.asList("I is juist, II is onjuist", "I is onjuist, II is juist", "I en II zijn juist", "I en II zijn onjuist"),
				1,
				"Denk aan de sfeer van de meeting en de criteria voor demonstratie."
		));
		Collections.shuffle(vragen);
	}
	public RetrospectivePuzzle() {
		super(vragen);
	}
}
