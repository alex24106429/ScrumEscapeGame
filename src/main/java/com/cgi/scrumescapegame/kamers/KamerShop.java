package com.cgi.scrumescapegame.kamers;

import java.util.ArrayList;
import java.util.Arrays;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.Shop;
import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.items.Chestplate;
import com.cgi.scrumescapegame.items.MiniBomb;
import com.cgi.scrumescapegame.items.GoldSword;
import com.cgi.scrumescapegame.items.GoldenGun;
import com.cgi.scrumescapegame.items.Bomb;
import com.cgi.scrumescapegame.items.HealingPotion;
import com.cgi.scrumescapegame.items.Item;
import com.cgi.scrumescapegame.items.Shield;
import com.cgi.scrumescapegame.items.Sword;
import com.cgi.scrumescapegame.items.Torch;

public class KamerShop extends Room {
    private final Shop shop = new Shop(new ArrayList<Item>(Arrays.asList(
        new Shield(0),
        new Chestplate(Randomizer.getWeightedRandomInt(10)),
        new Sword(0),
        new GoldSword(Randomizer.getWeightedRandomInt(10)),
        new HealingPotion(),
        new MiniBomb(),
        new Torch(),
        new Bomb(),
        new GoldenGun()
    )));
    
    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        setCleared(true);
        ImagePrinter.printImage("shopkeeper.png");
        shop.interactiveMode(Game.scanner, player);
    }

    public KamerShop(int roomX, int roomY) {
        super("Shop", "Tussen de kamers van de kerker vind je een onverwachte oase. Een wezen van pure transactie, de 'Proces-Handelaar', biedt hier zijn waren aan.\nHij ruilt de artefacten van de Architect voor het goud dat je verdient met vooruitgang.", roomX, roomY);
    }

    @Override
    public int getHue() {
        return 280;
    }
}
