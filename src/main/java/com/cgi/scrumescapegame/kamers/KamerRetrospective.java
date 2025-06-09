package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;
import com.cgi.scrumescapegame.puzzles.RetrospectivePuzzle;

public class KamerRetrospective extends Room {
    public KamerRetrospective(int roomX, int roomY) {
        super("Retrospective kamer", "Wow je bent in de retrospectivekamer", roomX, roomY);
        this.puzzle = new RetrospectivePuzzle().puzzle;
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        setCleared(true);
    }
    
    @Override
    public int getHue() {
        return 135;
    }
}