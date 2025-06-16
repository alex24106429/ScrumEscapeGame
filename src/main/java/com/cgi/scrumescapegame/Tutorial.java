package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class Tutorial {
	private boolean hasSeenDirectionTutorial = false;
	private boolean hasSeenItemTutorial = false;
	private boolean hasSeenEquipableItemTutorial = false;

	public void directionTutorial() {
		if (this.hasSeenDirectionTutorial) return;
		PrintMethods.printlnColor("Typ 'ga voor/achter/links/rechts' om naar de volgende kamer te lopen.", Attribute.DIM(), Attribute.ITALIC());
		this.hasSeenDirectionTutorial = true;
	}

	public void itemTutorial() {
		if (this.hasSeenItemTutorial) return;
		PrintMethods.printlnColor("Typ 'items' om je items te zien.\nTyp 'gebruik item (nummer)' om een item te gebruiken. ", Attribute.DIM(), Attribute.ITALIC());
		this.hasSeenItemTutorial = true;
	}

	public void equipableItemTutorial() {
		if (this.hasSeenEquipableItemTutorial) return;
		PrintMethods.printlnColor("Je hebt een Equipable Item (wapen of armor) gekregen. Deze doen niks totdat je ze equipped.\nDit kun je gewoon doen met de 'gebruik item (nummer)' commando.", Attribute.DIM(), Attribute.ITALIC());
		this.hasSeenEquipableItemTutorial = true;
	}
}
