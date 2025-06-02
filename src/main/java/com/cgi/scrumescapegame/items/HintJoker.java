package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Joker;
import com.cgi.scrumescapegame.Room;

public class HintJoker extends Item implements Joker, LimitedUseItem {
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

    @Override
    public int getUsesLeft() {
        return 0;
    }

    public HintJoker() {
        super(0);
    }
}
