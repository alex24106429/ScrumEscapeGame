package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.BacklogHydra;
import com.cgi.scrumescapegame.puzzles.DailyStandupPuzzle;
import com.cgi.scrumescapegame.puzzles.PuzzleRooms;

public class KamerDailyStandup extends Room implements PuzzleRooms {
    public KamerDailyStandup(int roomX, int roomY) {
        super("Kamer daily standup", "Je bent in de daily standup kamer. Hier bespreken we wat we sinds de volgende standup gedaan hebben en hoe we verder gaan werken.", roomX, roomY);
        this.puzzle = new DailyStandupPuzzle();
    }

    @Override
    public boolean canUseKeyJoker() {
        return true;
    }
    
    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
    }

    @Override
    public void startPuzzle(Player player, Difficulty difficulty) {
        puzzle.start(player, new BacklogHydra(), difficulty);
    }

    @Override
    public int getHue() {
        return 80;
    }
}