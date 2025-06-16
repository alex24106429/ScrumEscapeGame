package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.SlimeMaster;
import com.cgi.scrumescapegame.items.KeyJoker;
import com.cgi.scrumescapegame.puzzles.PuzzleRooms;
import com.cgi.scrumescapegame.puzzles.ReviewPuzzle;

public class KamerReview extends Room implements PuzzleRooms {

    public KamerReview(int roomX, int roomY) {
        super("Review Kamer", "Welkom in de Sprint Review kamer. Hier presenteer je het werkende product aan de stakeholders.", roomX, roomY);
        this.puzzle = new ReviewPuzzle();
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        if(player.hasChosenKeyJoker && !player.hasUsedKeyJoker) {
            player.addItem(new KeyJoker());
        }
    }

    @Override
    public void startPuzzle(Player player, Difficulty difficulty) {
        puzzle.start(player, new SlimeMaster(), difficulty);
        player.getItems().removeIf(item -> item instanceof KeyJoker);
    }

    @Override
    public boolean canUseKeyJoker() {
        return true;
    }

    @Override
    public int getHue() {
        return 200;
    }
}