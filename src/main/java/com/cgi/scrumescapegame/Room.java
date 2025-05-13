package com.cgi.scrumescapegame;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import com.cgi.scrumescapegame.Map;

public abstract class Room {
    protected String name;
    protected String description;
    protected Obstacle obstacle;
    protected int roomX = 0;
    protected int roomY = 0;
    protected HashMap<String, Boolean> adjacentRooms = new HashMap<>();


    public Room(String name, String description, int roomX, int roomY) {
        this.name = name;
        this.description = description;
        this.roomX = roomX;
        this.roomY = roomY;
    }

    public Point getCurrentPosition() {
        return new Point(roomX, roomY);
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
        // System.out.println("\n--- " + getName() + " ---");
		TextToImageRenderer.printGradientText(getName(), new Color(255, 255, 255), new Color(127, 127, 127), new Color(63, 63, 63), new Color(31, 31, 31), 2, true, false);
        PrintMethods.typeText(getDescription());
        System.out.println(player.getStatus());
    }

    public void createSpeechBubble(String[] texts) {
        int length = 0;
        for (String t : texts) {
            if (t.length() > length) {
                length = t.length();
            }
        }
        StringBuilder speechBubble = new StringBuilder();
        speechBubble.append("╭").append("─".repeat(length + 2)).append("╮\n");
        for (String t : texts) {
            speechBubble.append("│ ").append(t).append(" ".repeat(length - t.length())).append(" │\n");
        }
        speechBubble.append("╰").append("─".repeat(length + 2)).append("╯\n");
        speechBubble.append(" ".repeat(10)).append("  \\\n");
        speechBubble.append(" ".repeat(11)).append("  \\\n");

        System.out.print(speechBubble);
    }

    public void setAdjacentRoom(String direction, boolean status) {
        adjacentRooms.put(direction, status);
    }

    public String availableRooms() {

        StringBuilder availableRooms = new StringBuilder();

        if (adjacentRooms.getOrDefault("up", false)) {
            availableRooms.append("↑ ");
        }

        if (adjacentRooms.getOrDefault("left", false)) {
            availableRooms.append("← ");
        }

        if (adjacentRooms.getOrDefault("down", false)) {
            availableRooms.append("↓ ");
        }

        if (adjacentRooms.getOrDefault("right", false)) {
            availableRooms.append("→ ");
        }

        availableRooms.append("( ");

        if (adjacentRooms.getOrDefault("up", false)) {
            availableRooms.append("W ");
        }

        if (adjacentRooms.getOrDefault("left", false)) {
            availableRooms.append("A ");
        }

        if (adjacentRooms.getOrDefault("down", false)) {
            availableRooms.append("S ");
        }

        if (adjacentRooms.getOrDefault("right", false)) {
            availableRooms.append("D ");
        }

        availableRooms.append(")");

        return availableRooms.toString();
    }
}

