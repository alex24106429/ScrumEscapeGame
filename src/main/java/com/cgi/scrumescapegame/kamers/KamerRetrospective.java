package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.PuzzleRooms;
import com.cgi.scrumescapegame.Room;
import com.cgi.scrumescapegame.enemies.ScopeCreeper;
import com.cgi.scrumescapegame.puzzles.RetrospectivePuzzle;

public class KamerRetrospective extends Room implements PuzzleRooms {
    public KamerRetrospective(int roomX, int roomY) {
        super("Retrospective kamer", "Wow je bent in de retrospectivekamer", roomX, roomY);
        this.puzzle = new RetrospectivePuzzle();
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
        return 160;
    }
}