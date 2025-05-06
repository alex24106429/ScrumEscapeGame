package com.cgi.scrumescapegame;

public abstract class Room {
    protected String name;
    protected String description;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Wordt aangeroepen als een speler de kamer binnenkomt.
    // Voor nu print het de beschrijving, kan later uitgebreid worden.
    public void enterRoom(Player player) {
        System.out.println("\n--- " + getName() + " ---");
        player.showLives();
        System.out.println(getDescription());
    }
}