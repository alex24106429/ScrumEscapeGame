package com.cgi.scrumescapegame;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.graphics.TextToImageRenderer;

public abstract class Room {
    protected String name;
    protected String description;
    protected Puzzle puzzle;
    protected int roomX;
    protected int roomY;
    private boolean lookedAround = false;
    private boolean isCleared = false;
    public HashMap<String, Boolean> adjacentRooms = new HashMap<>();

    public Room(String name, String description, int roomX, int roomY) {
        this.name = name;
        this.description = description;
        this.roomX = roomX;
        this.roomY = roomY;
    }

    public Point getCurrentPosition() {
        return new Point(roomX, roomY);
    }

    public void setLookedAround(boolean lookedAround) {
        this.lookedAround = lookedAround;
    }

    public boolean hasLookedAround() {
        return lookedAround;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Wordt aangeroepen als een speler de kamer binnenkomt.
    // Voor nu print het de beschrijving, kan later uitgebreid worden.
    public void enterRoom(Player player, Difficulty difficulty) {
        // System.out.println("\n--- " + getName() + " ---");
		TextToImageRenderer.printGradientTextWithShadow(getName(), new Color(255, 255, 255), new Color(127, 127, 127), new Color(31, 31, 31), 2, true, false);
        PrintMethods.typeText(getDescription());
        player.printStatus();
        roomLogic(player, difficulty);
    }

    public abstract void roomLogic(Player player, Difficulty difficulty);
    
    public boolean canUseKeyJoker() {
        return false;
    }

    public void setAdjacentRoom(String direction, boolean status) {
        adjacentRooms.put(direction, status);
    }

    public boolean getCleared() {
        return this.isCleared;
    }

    public void setCleared(boolean isCleared) {
        this.isCleared = isCleared;
    }

}

