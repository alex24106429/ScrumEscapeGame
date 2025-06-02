package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Joker;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class KeyJoker extends Item implements Joker, LimitedUseItem {
    public KeyJoker(){
        super(0);
    }

    @Override
    public void useInRoom(Room room, Player player){
        if(room.canUseKeyJoker()) player.addItem(new Key());
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
        return "items/keyjoker.png";
    }

    @Override
    public int getUsesLeft() {
        return 0;
    }
}
