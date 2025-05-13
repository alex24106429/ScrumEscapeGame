package com.cgi.scrumescapegame;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class Player {
    private String name = "Avonturier";
    private Room currentRoom;
    private int lives;
    private int score;
    // Later: score, inventory, etc.

    public Player() {
        // Standaard gegevens
        this.lives = 3;
        this.score = 0;
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

    public void setName(String newName) {
        this.name = newName;
    }

    public int getLives() {
        return lives;
    }
    
    public int getScore() {
        return score;
    }

    public int changeScore(int amount) {
        return this.score += amount;
    }

    public String getStatus() {
        String output = "";
        // Speler naam
        output += Ansi.colorize("[ Speler: " + Ansi.colorize(name, Attribute.BOLD()), Attribute.BRIGHT_CYAN_TEXT())
                + Ansi.colorize(" ] ", Attribute.BRIGHT_CYAN_TEXT());

        // Kamernaam
        output += Ansi.colorize("[ Locatie: " + Ansi.colorize(currentRoom.getName(), Attribute.BOLD()),
                        Attribute.BRIGHT_BLUE_TEXT())
                + Ansi.colorize(" ] ", Attribute.BRIGHT_BLUE_TEXT());

        // Aantal levens
        output += Ansi.colorize("[ Levens: " + getLivesString() + " ] ", Attribute.BRIGHT_RED_TEXT());

        // Score
        output += Ansi.colorize("[ Score: " + Ansi.colorize("" + score, Attribute.BOLD()), Attribute.BRIGHT_YELLOW_TEXT())
                + Ansi.colorize(" ] ", Attribute.BRIGHT_YELLOW_TEXT());

        // Toegankelijke kamers
        output += Ansi.colorize("[ Toegankelijke Kamers: " + currentRoom.availableRooms() + " ] ",
                        Attribute.BRIGHT_GREEN_TEXT());

        // Coordinaten (alleen in debug)
        if(Game.debug) output +=
        Ansi.colorize("[ Coordianten: " + Ansi.colorize(currentRoom.getCurrentPosition().x + ", " + currentRoom.getCurrentPosition().y,
        Attribute.BOLD()), Attribute.BRIGHT_CYAN_TEXT()) + Ansi.colorize(" ] ", Attribute.BRIGHT_CYAN_TEXT());

        return output;
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
            System.out.println(Ansi.colorize("Je hebt een leven verloren! Je hebt nog " + lives + " levens over.",
                    Attribute.BRIGHT_RED_TEXT()));
            System.out.println(getStatus());
        } else {
            System.out.println(Ansi.colorize("Game over! Je hebt geen levens meer.", Attribute.BRIGHT_RED_TEXT()));
        }
    }

    public void gainLife() {
        if (lives < 3) {
            lives++;
            System.out.println(Ansi.colorize("Je hebt een leven gewonnen! Je hebt nu " + lives + " levens.",
                    Attribute.BRIGHT_GREEN_TEXT()));
        } else {
            System.out.println(Ansi.colorize("Je hebt al het maximale aantal levens.", Attribute.BRIGHT_GREEN_TEXT()));
        }
    }
}