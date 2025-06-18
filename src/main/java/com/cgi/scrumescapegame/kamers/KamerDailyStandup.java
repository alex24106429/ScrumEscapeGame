package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.BacklogHydra;
import com.cgi.scrumescapegame.items.KeyJoker;
import com.cgi.scrumescapegame.puzzles.DailyStandupPuzzle;
import com.cgi.scrumescapegame.puzzles.PuzzleRooms;

public class KamerDailyStandup extends Room implements PuzzleRooms {
    public KamerDailyStandup(int roomX, int roomY) {
        super("Kamer daily standup", "Een cirkelvormige kamer, met in het midden een gigantisch, pulserend uurwerk dat de tijd in blokken van 24 uur wegtikt.\nDit is het Hart van het Ritme. De dagelijkse synchronisatie houdt de muren hier sterk.\nVerspil geen tijd, want elke seconde van onduidelijkheid voedt de veelkoppige Backlog Hydra die loert in de schaduwen van onafgemaakt werk.", roomX, roomY);
        this.puzzle = new DailyStandupPuzzle();
    }

    @Override
    public boolean canUseKeyJoker() {
        return true;
    }
    
    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        if(player.hasChosenKeyJoker && !player.hasUsedKeyJoker) {
            player.addItem(new KeyJoker());
        }
    }

    @Override
    public void startPuzzle(Player player, Difficulty difficulty) {
        puzzle.start(player, new BacklogHydra(), difficulty);
        player.getItems().removeIf(item -> item instanceof KeyJoker);
    }

    @Override
    public int getHue() {
        return 80;
    }
}