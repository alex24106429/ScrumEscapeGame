package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.ScopeCreeper;
import com.cgi.scrumescapegame.puzzles.PuzzleRooms;
import com.cgi.scrumescapegame.puzzles.ScrumboardPuzzle;

public class KamerScrumboard extends Room implements PuzzleRooms {
    public KamerScrumboard(int roomX, int roomY) {
        super("Scrumboard kamer", "Je bent in de Scrumboard kamer. Hier kan je zien welke taken en user stories er nog aan gewerkt moeten worden.", roomX, roomY);
        this.puzzle = new ScrumboardPuzzle();
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
        return 240;
    }
}