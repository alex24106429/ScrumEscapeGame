package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Joker;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.kamers.Room;

public class KeyJoker extends Item implements Joker, UsableItem {
    public KeyJoker(){
        super(0);
    }

    @Override
    public void useInRoom(Room room, Player player){
        if(room.canUseKeyJoker()) player.addItem(new Key());
        player.getItems().removeIf(item -> item instanceof KeyJoker);
        player.hasUsedKeyJoker = true;
    }

    @Override
    public void useItem(Player player) {
        useInRoom(player.getCurrentRoom(), player);
    }

    @Override
    public String getName(){
        return "Sleutel Joker";
    }

    @Override
    public String getDescription(){
        return "Krijg een sleutel";
    }

    @Override
    public String getImagepath(){
        return "items/keyjoker.png";
    }
}
