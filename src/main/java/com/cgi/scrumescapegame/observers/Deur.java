package com.cgi.scrumescapegame.observers;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.Player;

public class Deur implements PuzzleObserver {

    public void update(boolean isCorrect, Vraag vraag, Player player) {
        if (isCorrect) {
            player.getCurrentRoom().setCleared(true);
        }
        else {
            // Dit runnen wij nooit...
            // System.out.println("De deuren blijven gesloten.");
        }
    }
}
