package com.cgi.scrumescapegame.puzzles.testing;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.observers.PuzzleObserver;
import com.cgi.scrumescapegame.vragen.Vraag;

public class MockPuzzleObserver implements PuzzleObserver {
    public boolean isUpdateAangeroepen = false;
    public boolean correctDoorgegeven = false;

    @Override
    public void update(boolean isCorrect, Vraag vraag, Player player) {
        isUpdateAangeroepen = true;
        correctDoorgegeven = isCorrect;
    }
}

