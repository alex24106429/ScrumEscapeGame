package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class EindKamer extends Room {
    public EindKamer(int roomX, int roomY) {
        super("Final Room", "This is the final room.", roomX, roomY);
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
    }
}