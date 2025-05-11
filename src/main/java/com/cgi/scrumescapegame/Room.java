package com.cgi.scrumescapegame;

import java.awt.Color;

public abstract class Room {
    protected String name;
    protected String description;
    protected Obstacle obstacle;

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
        // System.out.println("\n--- " + getName() + " ---");
		TextToImageRenderer.printGradientText(getName(), new Color(255, 255, 255), new Color(127, 127, 127), new Color(63, 63, 63), new Color(31, 31, 31), 2, true, false);
        System.out.println(getDescription());
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

}

