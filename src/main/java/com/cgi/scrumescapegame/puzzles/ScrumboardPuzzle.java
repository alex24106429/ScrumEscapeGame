package com.cgi.scrumescapegame.puzzles;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.vragen.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScrumboardPuzzle extends GeneralPuzzle{
    private static final List<Vraag> vragen = new ArrayList<>();
	static {

		vragen.add(new Matching(
				Arrays.asList(
						"Deze persoon nodigt de Stakeholders uit...",
						"Deze persoon zorgt ervoor dat de meeting...",
						"Tijdens de Sprint review bespreekt deze persoon..."
				),
				Arrays.asList("Developer", "Product Owner", "Scrum Master"),
				Arrays.asList("1B", "2C", "3A"),
				"Koppel de verantwoordelijkheden tijdens de Sprint Review aan de juiste Scrum rollen."
		));
		vragen.add(new OpenVraag(
				"Wat is de belangrijkste reden dat de Scrum Master de Daily Scrum faciliteert?",
				"De Scrum Master faciliteert de Daily Scrum om ervoor te zorgen dat het team gefocust blijft op de voortgang en obstakels tijdig worden besproken.",
				"hint: De Scrum Master is verantwoordelijk voor het faciliteren van de Daily Scrum..."
		));
		vragen.add(new MeerkeuzeVraag(
				"Wat is de belangrijkste taak van de Scrum Master tijdens de Sprint Planning?",
				Arrays.asList(
						"De Scrum Master zorgt ervoor dat het team de juiste user stories selecteert.",
						"De Scrum Master faciliteert de Sprint Planning en zorgt ervoor dat het team begrijpt wat er moet gebeuren.",
						"De Scrum Master schrijft de user stories voor het team."
				),
				2,
				"De Scrum Master faciliteert de Sprint Planning en zorgt ervoor dat het team begrijpt wat er moet gebeuren."
		));
		Collections.shuffle(vragen);
	}
    public ScrumboardPuzzle() {
        super(vragen);
    }
}
