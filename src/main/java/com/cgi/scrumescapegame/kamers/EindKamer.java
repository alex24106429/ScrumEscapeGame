package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class EindKamer extends Room {
    public EindKamer(int roomX, int roomY) {
        super("TIA kamer", "TIA staat voor transparancy, inspection en Adaptation. Dit zijn de drie pilaren van scrum", roomX, roomY);
    }

    @Override
    public void enterRoom(Player player) {
        super.enterRoom(player);
        // Specifieke acties voor de dailystandupkamer
    }
}