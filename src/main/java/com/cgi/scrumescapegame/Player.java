package com.cgi.scrumescapegame;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class Player {
    private final String name;
    private Room currentRoom;
    private int lives;
    // Later: score, inventory, etc.

    public Player(String name) {
        this.name = name;
        this.lives = 3; // Standaard levens
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
		return
		Ansi.colorize("[ Speler: " + Ansi.colorize(name, Attribute.BOLD()), Attribute.BRIGHT_YELLOW_TEXT()) + Ansi.colorize(" ] ", Attribute.BRIGHT_YELLOW_TEXT()) +

		Ansi.colorize("[ Locatie: " + Ansi.colorize(currentRoom.getName(), Attribute.BOLD()), Attribute.BRIGHT_BLUE_TEXT()) + Ansi.colorize(" ] ", Attribute.BRIGHT_BLUE_TEXT()) +

		Ansi.colorize("[ Levens: " + getLivesString() + " ] ", Attribute.BRIGHT_RED_TEXT());
    }

	public String getLivesString() {
		String output = "";
        for (int i = 0; i < lives; i++) {
			output += "♥ ";
        }
        for (int i = lives; i < 3; i++) {
			output += "♡ ";
        }
        return output.trim();
    }

    public void showLives() {
		System.out.println(Ansi.colorize(getLivesString(), Attribute.BRIGHT_RED_TEXT()));
    }

    public void loseLife() {
        if (lives > 0) {
            lives--;
			System.out.println(Ansi.colorize("Je hebt een leven verloren! Je hebt nog " + lives + " levens over.", Attribute.BRIGHT_RED_TEXT()));
        } else {
			System.out.println(Ansi.colorize("Game over! Je hebt geen levens meer.", Attribute.BRIGHT_RED_TEXT()));
        }
    }
    public void gainLife() {
        if (lives < 3) {
            lives++;
			System.out.println(Ansi.colorize("Je hebt een leven gewonnen! Je hebt nu " + lives + " levens.", Attribute.BRIGHT_GREEN_TEXT()));
        } else {
			System.out.println(Ansi.colorize("Je hebt al het maximale aantal levens.", Attribute.BRIGHT_GREEN_TEXT()));
        }
    }
}