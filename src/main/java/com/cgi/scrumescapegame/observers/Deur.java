package com.cgi.scrumescapegame.observers;

import com.cgi.scrumescapegame.Vraag;

public class Deur implements PuzzleObserver {
    public void update(boolean isCorrect, Vraag vraag) {
        // Implement the logic to update the door state based on the puzzle result
        if (isCorrect) {
            System.out.println("De de deuren gaan open!");
        }
        else {
            System.out.println("De deuren blijven gesloten.");
        }
    }
}
