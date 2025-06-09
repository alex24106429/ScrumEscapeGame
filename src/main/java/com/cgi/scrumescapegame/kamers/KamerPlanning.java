package com.cgi.scrumescapegame.kamers;

import java.util.ArrayList;
import java.util.Arrays;

import com.cgi.scrumescapegame.*;
import com.cgi.scrumescapegame.enemies.ScopeCreeper;
import com.cgi.scrumescapegame.items.*;
import com.cgi.scrumescapegame.puzzles.PlanningPuzzle;

public class KamerPlanning extends Room implements PuzzleRooms {
    public KamerPlanning(int roomX, int roomY) {
        super("Kamer Planning", "Je bent in de Planning Poker kamer. Hier wordt de scope van de sprint bepaald. Wat ga je doen?", roomX, roomY);
        this.puzzle = new PlanningPuzzle().puzzle;
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
    public void startPuzzle(Player player, Difficulty difficulty) {
        puzzle.start(player, new ScopeCreeper(), difficulty);
    }

    @Override
    public int getHue() {
        return 90;
    }
}