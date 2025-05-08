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
        // ASCII Dier (Hond bijvoorbeeld)
        String dog =
                " / \\__\n" +
                        "(    @\\___\n" +
                        " /         O\n" +
                        "/   (_____/\n" +
                        "/_____/   U\n";
        String cat =
                " /\\_/\\\n" +
                        "( o.o )\n" +
                        " > ^ <\n";
        String fish = "><(((('>\n";
        // Voeg het dier toe, maar zorg ervoor dat het goed uitgelijnd is
        int padding = 15; // Zorg ervoor dat het dier gecentreerd is
        for (String line : cat.split("\n")) {
            speechBubble.append(" ".repeat(padding)).append(line).append("\n");
        }
        System.out.println(speechBubble);

    }
}

