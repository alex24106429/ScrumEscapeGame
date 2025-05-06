package com.cgi.scrumescapegame;

public class Player {
    private String name;
    private Room currentRoom;
    // Later: score, inventory, etc.

    public Player(String name) {
        this.name = name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
        if (this.currentRoom != null) {
            System.out.println(this.currentRoom.getDescription());
        }
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        if (currentRoom != null) {
            return "Speler: " + name + "\nLocatie: " + currentRoom.getName() + "\nStatus: Je bent de wondere wereld van Scrum aan het ontdekken.";
        }
        return "Speler: " + name + "\nStatus: Nog niet in het spel.";
    }
}