package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.*;
import com.cgi.scrumescapegame.enemies.ScopeCreeper;
import com.cgi.scrumescapegame.puzzles.PlanningPuzzle;

public class KamerPlanning extends Room implements PuzzleRooms {
    public KamerPlanning(int roomX, int roomY) {
        super("Kamer Planning", "Je bent in de Planning kamer. Hier wordt de scope van de sprint bepaald. Wat ga je doen?", roomX, roomY);
        this.puzzle = new PlanningPuzzle();
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
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