package com.cgi.scrumescapegame.observers;

import com.cgi.scrumescapegame.Vraag;

public interface PuzzleObserver {
    void update(boolean isCorrect, Vraag vraag);
}

