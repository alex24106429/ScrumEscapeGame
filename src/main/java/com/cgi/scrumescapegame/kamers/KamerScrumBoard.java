package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class KamerScrumBoard extends Room {
    public KamerScrumBoard(int roomX, int roomY) {
        super("Kamer Scrum Board", "Je bent in de Scrum Board kamer. Hier wordt het werk van het team bijgehouden. Wat ga je doen?", roomX, roomY);
    }

    @Override
    public void enterRoom(Player player) {
        super.enterRoom(player);
        // Specifieke acties voor de planningskamer
    }
}
