package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class Tutorial {
	private boolean hasSeenDirectionTutorial = false;
	private boolean hasSeenItemTutorial = false;
	private boolean hasSeenEquipableItemTutorial = false;
	private boolean hasSeenMapTutorial = false;
	private boolean hasSeenLookAroundTutorial = false;
	private boolean hasSeenBlackjackTutorial = false;

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

	public void mapTutorial() {
		if (this.hasSeenMapTutorial) return;
		PrintMethods.printlnColor("Hierboven is de map. Iedere gekleurde tegel is een kamer. Jouw locatie is de witte tegel.\nDe mogelijke richtingen staan hieronder.", Attribute.DIM(), Attribute.ITALIC());
		this.hasSeenMapTutorial = true;
	}

	public void lookAroundTutorial() {
		if (this.hasSeenLookAroundTutorial) return;
		PrintMethods.printlnColor("Typ 'kijk rond' om rond te kijken in de kamer, misschien liggen er items.", Attribute.DIM(), Attribute.ITALIC());
		this.hasSeenLookAroundTutorial = true;
	}

	public void blackjackTutorial() {
		if (this.hasSeenBlackjackTutorial) return;
		PrintMethods.printlnColor("1. Het Doel: Kom zo dicht mogelijk bij 21 punten, zonder eroverheen te gaan. Je speelt tegen de dealer.", Attribute.WHITE_TEXT());
		PrintMethods.printlnColor("2. Kaartwaarden:", Attribute.WHITE_TEXT());
		PrintMethods.printlnColor("   - Kaarten 2 t/m 10 hebben hun eigen waarde.", Attribute.DIM());
		PrintMethods.printlnColor("   - Boer, Vrouw en Koning zijn 10 punten waard.", Attribute.DIM());
		PrintMethods.printlnColor("   - Aas is 1 of 11 punten waard, wat voor jou het beste uitkomt.", Attribute.DIM());
		PrintMethods.printlnColor("3. Jouw Beurt:", Attribute.WHITE_TEXT());
		PrintMethods.printlnColor("   - 'Hit' (h): Vraag om een extra kaart.", Attribute.DIM());
		PrintMethods.printlnColor("   - 'Stand' (s): Je past en neemt geen kaarten meer. De beurt gaat naar de dealer.", Attribute.DIM());
		PrintMethods.printlnColor("4. Bust: Als je totaal boven de 21 komt, ben je 'bust' en verlies je direct je inzet.", Attribute.WHITE_TEXT());
		PrintMethods.printlnColor("5. De Dealer: De dealer moet kaarten nemen ('hit') totdat hij 17 of meer punten heeft. Daarna moet hij passen ('stand').", Attribute.WHITE_TEXT());
		PrintMethods.printlnColor("6. Winnen: Je wint als jouw puntentotaal hoger is dan dat van de dealer (zonder 'bust' te gaan), of als de dealer 'bust' gaat.", Attribute.WHITE_TEXT());
		this.hasSeenBlackjackTutorial = true;
	}
}
