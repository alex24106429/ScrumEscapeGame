package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Joker;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.kamers.Room;
import com.cgi.scrumescapegame.puzzles.PuzzleRooms;
import com.diogonunes.jcolor.Attribute;

public class HintJoker extends Item implements Joker, UsableItem {
    @Override
    public void useItem(Player player) {
        useInRoom(player.getCurrentRoom(), player);
    }

    @Override
    public void useInRoom(Room room, Player player) {
        if (!(room instanceof PuzzleRooms)) {
            PrintMethods.printlnColor("Dit kamer bevat geen vragen.", Attribute.BRIGHT_RED_TEXT());
            return;
        }
        if (room.getCleared()) {
            PrintMethods.printlnColor("Je kunt de Hint Joker niet gebruiken in kamers die al voltooid zijn.",
                    Attribute.BRIGHT_RED_TEXT());
            return;
        }
        if (room.getHasUsedHintJoker()) { // Dit hoort nooit te gebeuren, tenzij de speler meer dan 1 hint joker per
                                          // game kan krijgen
            PrintMethods.printlnColor("Je hebt de Hint Joker al gebruikt in deze kamer.", Attribute.BRIGHT_RED_TEXT());
            return;
        }
        room.setHasUsedHintJoker(true);
        player.getItems().removeIf(item -> item instanceof HintJoker);
        PrintMethods.printlnColor("Je hebt de Hint Joker gebruikt, je krijgt hints in de puzzel!", Attribute.BRIGHT_GREEN_TEXT());
    }

    @Override
    public String getName() {
        return "Hint Joker";
    }

    @Override
    public String getDescription() {
        return "Krijg gratis hints bij de vragen in 1 kamer.";
    }

    @Override
    public String getImagepath() {
        return "items/hintjoker.png";
    }

    public HintJoker() {
        super(0);
    }
}
