package com.cgi.scrumescapegame.kamers;

import java.util.ArrayList;
import java.util.Arrays;

import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;
import com.cgi.scrumescapegame.Shop;
import com.cgi.scrumescapegame.items.DamagePotion;
import com.cgi.scrumescapegame.items.HealingPotion;
import com.cgi.scrumescapegame.items.Item;
import com.cgi.scrumescapegame.items.Shield;
import com.cgi.scrumescapegame.items.Sword;
import com.cgi.scrumescapegame.items.Torch;

public class KamerPlanning extends Room {
    public KamerPlanning(int roomX, int roomY) {
        super("Kamer Planning", "Je bent in de Planning Poker kamer. Hier wordt de scope van de sprint bepaald. Wat ga je doen?", roomX, roomY);
    }

    @Override
    public void roomLogic(Player player) {
        Shop planningKamerShop = new Shop(new ArrayList<Item>(Arrays.asList(
            new Shield(),
            new Sword(),
            new HealingPotion(),
            new DamagePotion(),
            new Torch()
        )));

        planningKamerShop.interactiveMode(Game.scanner, player);
        // Specifieke acties voor de planningskamer
    }
}