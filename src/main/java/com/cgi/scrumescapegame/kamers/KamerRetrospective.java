package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class KamerRetrospective extends Room {
    public KamerRetrospective(int roomX, int roomY) {
        super("Retrospective kamer", "Wow je bent in de retrospectivekamer", roomX, roomY);
    }

    @Override
    public void enterRoom(Player player) {
        super.enterRoom(player);
        // Specifieke acties voor de retrospectiveskamer
    }
}