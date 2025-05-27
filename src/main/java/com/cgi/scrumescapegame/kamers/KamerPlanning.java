package com.cgi.scrumescapegame.kamers;

import java.util.ArrayList;
import java.util.Arrays;

import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;
import com.cgi.scrumescapegame.Shop;
import com.cgi.scrumescapegame.items.*;

public class KamerPlanning extends Room {
    public KamerPlanning(int roomX, int roomY) {
        super("Kamer Planning", "Je bent in de Planning Poker kamer. Hier wordt de scope van de sprint bepaald. Wat ga je doen?", roomX, roomY);
    }

    @Override
    public void roomLogic(Player player) {
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
}