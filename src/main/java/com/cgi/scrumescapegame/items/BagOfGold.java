package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;

public class BagOfGold extends Item implements UsableItem, LimitedUseItem {

    private final int goldAmount;
    @Override
    public String getName() {
        return "Zak met goud";
    }

    @Override
    public String getDescription() {
        return "+" + goldAmount + " goud";
    }

    @Override
    public String getImagepath() {
        return "items/goldbag.png";
    }

    @Override
    public void useItem(Player player) {
        player.changeGold(goldAmount);
    }

    @Override
    public int getUsesLeft() {
        return 0;
    }

    public BagOfGold() {
        super(Randomizer.getRandomInt(15, 75));
        this.goldAmount = getCurrentValue();
    }
}