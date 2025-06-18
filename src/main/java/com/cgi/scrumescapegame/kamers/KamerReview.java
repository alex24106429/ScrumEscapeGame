package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.SlimeMaster;
import com.cgi.scrumescapegame.items.KeyJoker;
import com.cgi.scrumescapegame.puzzles.PuzzleRooms;
import com.cgi.scrumescapegame.puzzles.ReviewPuzzle;

public class KamerReview extends Room implements PuzzleRooms {

    public KamerReview(int roomX, int roomY) {
        super("Review Kamer", "Je staat in een amfitheater, badend in een helder, onthullend licht.\nDit is de Arena van de Waarde, waar het resultaat van je werk wordt geïnspecteerd. De echo's van talloze stakeholders hangen in de lucht.\nToon wat je hebt gebouwd en verzamel hun feedback, voordat de vage wensen en onduidelijke eisen samensmelten tot een glibberige Slime Master.", roomX, roomY);
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