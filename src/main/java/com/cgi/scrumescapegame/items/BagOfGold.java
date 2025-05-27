package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;

public class BagOfGold extends Item implements UsableItem, LimitedUseItem {

    private final int goldAmount;
    @Override
    public String getName() {
        return "Bag of Gold";
    }

    @Override
    public String getDescription() {
        return "+" + goldAmount + " gold";
    }

    @Override
    public String getImagepath() {
        return "items/goldbag.png";
    }

    @Override
    public void useItem(Player player) {
        player.gainGold(goldAmount);
    }

    @Override
    public int getUsesLeft() {
        return 1;
    }

    public BagOfGold() {
        super(0);
        int min = 2;
        int max = 10;
        int randGetal = (int) (Math.random() * (max - min + 1)) + min;
        this.goldAmount = randGetal * 5; // Random amount of gold between 10 and 50

    }
}