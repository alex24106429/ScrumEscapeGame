package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Joker;
import com.cgi.scrumescapegame.Room;

public class KeyJoker extends Item implements Joker, LimitedUseItem {
    public KeyJoker(){
        super(0);
    }

    @Override
    public void useInRoom(Room room){

    }

    @Override
    public String getName(){
        return "Key Joker";
    }

    @Override
    public String getDescription(){
        return "Get an extra key";
    }

    @Override
    public String getImagepath(){
        return "";
    }

    @Override
    public int getUsesLeft() {
        return 0;
    }
}
