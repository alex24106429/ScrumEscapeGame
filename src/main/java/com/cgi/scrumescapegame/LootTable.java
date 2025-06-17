package com.cgi.scrumescapegame;

import java.util.ArrayList;
import java.util.Arrays;

import com.cgi.scrumescapegame.items.Apple;
import com.cgi.scrumescapegame.items.BagOfGold;
import com.cgi.scrumescapegame.items.Item;

public class LootTable {
    public static final ArrayList<Item> roomLoot = new ArrayList<>(Arrays.asList(new Apple(), new BagOfGold()));
}
