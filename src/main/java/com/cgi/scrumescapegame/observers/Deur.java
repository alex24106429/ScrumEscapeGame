package com.cgi.scrumescapegame.observers;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.Player;

public class Deur implements PuzzleObserver {

    public void update(boolean isCorrect, Vraag vraag, Player player) {
        // Implement the logic to update the door state based on the puzzle result
        if (isCorrect) {
            System.out.println("De deuren gaan open!");
        }
        else {
            System.out.println("De deuren blijven gesloten.");
        }
    }
}
