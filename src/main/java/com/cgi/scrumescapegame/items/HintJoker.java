package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Joker;
import com.cgi.scrumescapegame.Room;

public class HintJoker extends Item implements Joker {
    @Override
    public void useInRoom(Room room) {

    }

    @Override
    public String getName() {
        return "Hint Joker";
    }

    @Override
    public String getDescription() {
        return "Shows a hint.";
    }

    @Override
    public String getImagepath() {
        return "items/hintjoker.png";
    }

    public HintJoker() {
        super(0);
    }
}
