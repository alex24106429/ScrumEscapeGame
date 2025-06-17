package com.cgi.scrumescapegame.kamers;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.graphics.TextToImageRenderer;
import com.cgi.scrumescapegame.puzzles.Puzzle;

public abstract class Room {
    protected String name;
    protected String description;
    protected Puzzle puzzle;
    public int roomX;
    public int roomY;
    private boolean lookedAround = false;
    private boolean isCleared = false;
    private boolean hasUsedHintJoker = false;
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
    public void setCurrentPosition(int x, int y) {
        this.roomX = x;
        this.roomY = y;
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
    public void enterRoom(Player player, Difficulty difficulty) {
        if(getCleared()) {
            System.out.println(getDescription());
        } else {
            PrintMethods.typeText(getDescription());
        }
		TextToImageRenderer.printGradientTextWithShadow(getName(), new Color(255, 255, 255), new Color(127, 127, 127), new Color(31, 31, 31), 2, true, false);
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

    public abstract int getHue();

    public boolean getHasUsedHintJoker() {
        return this.hasUsedHintJoker;
    }

    public void setHasUsedHintJoker(boolean value) {
        this.hasUsedHintJoker = value;
    }

}

