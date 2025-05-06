package com.cgi.scrumescapegame;

public class Player {
    private String name;
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
        if (currentRoom != null) {
            return "Speler: " + name + "\nLocatie: " + currentRoom.getName() + "\nLevens: " + this.lives + "/3 levens." + "\nStatus: Je bent de wondere wereld van Scrum aan het ontdekken.";
        }
        return "Speler: " + name + "\nStatus: Nog niet in het spel.";
    }
    public void showLives() {
        for (int i = 0; i < lives; i++) {
            System.out.print("♥ ");
        }
        for (int i = lives; i < 3; i++) {
            System.out.print("♡ ");
        }
        System.out.println();
    }
    public void loseLife() {
        if (lives > 0) {
            lives--;
            System.out.println("Je hebt een leven verloren! Je hebt nog " + lives + " levens over.");
        } else {
            System.out.println("Game over! Je hebt geen levens meer.");
        }
    }
    public void gainLife() {
        if (lives < 3) {
            lives++;
            System.out.println("Je hebt een leven gewonnen! Je hebt nu " + lives + " levens.");
        } else {
            System.out.println("Je hebt al het maximale aantal levens.");
        }
    }
}