package com.cgi.scrumescapegame.kamers;

import java.util.ArrayList;
import java.util.Arrays;

import com.cgi.scrumescapegame.*;
import com.cgi.scrumescapegame.items.*;

public class KamerPlanning extends Room {
    public KamerPlanning(int roomX, int roomY) {
        super("Kamer Planning", "Je bent in de Planning Poker kamer. Hier wordt de scope van de sprint bepaald. Wat ga je doen?", roomX, roomY);
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        setCleared(true);
        Shop planningKamerShop = new Shop(new ArrayList<Item>(Arrays.asList(
            new Shield(),
            new Chestplate(),
            new Sword(),
            new GoldSword(),
            new HealingPotion(),
            new DamagePotion(),
            new Torch(),
            new Grenade(),
            new GoldenGun()
        )));

        planningKamerShop.interactiveMode(Game.scanner, player);
        // Specifieke acties voor de planningskamer
    }

    @Override
    public int getHue() {
        return 90;
    }
}