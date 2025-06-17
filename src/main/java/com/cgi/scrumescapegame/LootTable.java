package com.cgi.scrumescapegame;

import java.util.ArrayList;
import java.util.Arrays;

import com.cgi.scrumescapegame.items.Apple;
import com.cgi.scrumescapegame.items.BagOfGold;
import com.cgi.scrumescapegame.items.DamagePotion;
import com.cgi.scrumescapegame.items.HealingPotion;
import com.cgi.scrumescapegame.items.Item;
import com.cgi.scrumescapegame.items.Shield;
import com.cgi.scrumescapegame.items.Sword;
import com.cgi.scrumescapegame.items.Torch;

public class LootTable {
    public static final ArrayList<Item> roomLoot = new ArrayList<>(Arrays.asList(
        new Apple(),
        new BagOfGold(),
        new Torch()
    ));
    public static final ArrayList<Item> battleLoot = new ArrayList<>(Arrays.asList(
        new Sword(Randomizer.getWeightedRandomInt(10)),
        new HealingPotion(),
        new DamagePotion(),
        new Shield(Randomizer.getWeightedRandomInt(10))
    ));
}
