package com.cgi.scrumescapegame.observers;

import com.cgi.scrumescapegame.Vraag;

public class Deur implements PuzzleObserver {
    public void update(boolean isCorrect, Vraag vraag) {
        // Implement the logic to update the door state based on the puzzle result
        if (isCorrect) {
            System.out.println("De deur gaat open!");
        } else {
            System.out.println("De deur blijft gesloten.");
            System.out.println("Kijk uit voor het monster!");
        }
    }
}
