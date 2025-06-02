package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Joker;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class KeyJoker extends Item implements Joker, LimitedUseItem {
    public KeyJoker(){
        super(0);
    }

    @Override
    public void useInRoom(Room room, Player player){
        if(room.canUseKeyJoker()) {
        } else {
            PrintMethods.printlnColor("You can't use the Key Joker here.", Attribute.BRIGHT_RED_TEXT());
        }

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
