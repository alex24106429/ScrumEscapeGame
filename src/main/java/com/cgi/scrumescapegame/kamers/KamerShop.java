package com.cgi.scrumescapegame.kamers;

import java.util.ArrayList;
import java.util.Arrays;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.PuzzleRooms;
import com.cgi.scrumescapegame.Room;
import com.cgi.scrumescapegame.Shop;
import com.cgi.scrumescapegame.items.Chestplate;
import com.cgi.scrumescapegame.items.DamagePotion;
import com.cgi.scrumescapegame.items.GoldSword;
import com.cgi.scrumescapegame.items.GoldenGun;
import com.cgi.scrumescapegame.items.Grenade;
import com.cgi.scrumescapegame.items.HealingPotion;
import com.cgi.scrumescapegame.items.Item;
import com.cgi.scrumescapegame.items.Shield;
import com.cgi.scrumescapegame.items.Sword;
import com.cgi.scrumescapegame.items.Torch;
import com.cgi.scrumescapegame.puzzles.PlanningPuzzle;

public class KamerShop extends Room {
    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        setCleared(true);
        Shop shop = new Shop(new ArrayList<Item>(Arrays.asList(
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

        shop.interactiveMode(Game.scanner, player);
    }

    public KamerShop(int roomX, int roomY) {
        super("Shop", "Je bent in de shop, hier kun je items kopen en verkopen.", roomX, roomY);
    }

    @Override
    public int getHue() {
        return 90;
    }
}
