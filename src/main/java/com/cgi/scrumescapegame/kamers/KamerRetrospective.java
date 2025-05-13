package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class KamerRetrospective extends Room {
    public KamerRetrospective(int roomX, int roomY) {
        super("Kamer Retrospective", "Je bent in de Retrospective kamer. Hier wordt de afgelopen sprint geëvalueerd. Wat ga je doen?", roomX, roomY);
    }

    @Override
    public void enterRoom(Player player) {
        super.enterRoom(player);
        // Specifieke acties voor de planningskamer
    }
}