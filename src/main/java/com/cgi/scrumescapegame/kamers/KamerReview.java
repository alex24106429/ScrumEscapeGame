package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;
import com.cgi.scrumescapegame.puzzles.ReviewPuzzle;

public class KamerReview extends Room {

    public KamerReview(int roomX, int roomY) {
        super("Review Kamer", "Welkom in de Sprint Review kamer. Hier presenteer je het werkende product aan de stakeholders.", roomX, roomY);
        ReviewPuzzle reviewPuzzle = new ReviewPuzzle();
        puzzle = reviewPuzzle.puzzle;
    }

    @Override
    public void roomLogic(Player player) {
        puzzle.start(player);
        player.printStatus();
    }
}