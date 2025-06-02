package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Joker;
import com.cgi.scrumescapegame.Room;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.hints.FunnyHintProvider;
import com.diogonunes.jcolor.Attribute;

public class HintJoker extends Item implements Joker, LimitedUseItem {
    FunnyHintProvider funnyHintProvider = new FunnyHintProvider();

    @Override
    public void useInRoom(Room room) {
        PrintMethods.printlnColor(funnyHintProvider.getHint(), Attribute.BLUE_TEXT());
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
