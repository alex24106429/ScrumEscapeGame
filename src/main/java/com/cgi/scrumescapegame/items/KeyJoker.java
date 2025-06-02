package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Joker;
import com.cgi.scrumescapegame.KeyJokerRoom;
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
        if(room instanceof KeyJokerRoom) {
            player.addItem(new Key());
        } else {
            PrintMethods.printlnColor("Je kan hier niet de Sleutel Joker gebruiken.", Attribute.BRIGHT_RED_TEXT());
        }

    }

    @Override
    public String getName(){
        return "Sleutel Joker";
    }

    @Override
    public String getDescription(){
        return "Krijg een extra sleutel.";
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
