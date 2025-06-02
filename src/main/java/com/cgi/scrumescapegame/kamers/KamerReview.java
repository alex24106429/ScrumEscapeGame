package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.PuzzleRooms;
import com.cgi.scrumescapegame.Room;
import com.cgi.scrumescapegame.enemies.ScopeCreeper;
import com.cgi.scrumescapegame.puzzles.ReviewPuzzle;

public class KamerReview extends Room implements PuzzleRooms {

    public KamerReview(int roomX, int roomY) {
        super("Review Kamer", "Welkom in de Sprint Review kamer. Hier presenteer je het werkende product aan de stakeholders.", roomX, roomY);
        ReviewPuzzle reviewPuzzle = new ReviewPuzzle();
        puzzle = reviewPuzzle.puzzle;
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
    }

    @Override
    public void startPuzzle(Player player, Difficulty difficulty) {
        // Start de review puzzel
        puzzle.start(player, new ScopeCreeper(), difficulty);
    }

    @Override
    public boolean canUseKeyJoker() {
        return true;
    }
}