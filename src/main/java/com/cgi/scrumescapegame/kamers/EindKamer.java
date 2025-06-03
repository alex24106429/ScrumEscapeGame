package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class EindKamer extends Room {
    public EindKamer(int roomX, int roomY) {
        super("Laatse Kamer", "Dit is de Laatse Kamer. JE BENT ER BIJNA!", roomX, roomY);
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        setCleared(true);
    }

    @Override
    public int getHue() {
        return 0;
    }
}