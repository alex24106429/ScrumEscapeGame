package com.cgi.scrumescapegame.observers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.vragen.Vraag;

public interface PuzzleObserver {
    void update(boolean isCorrect, Vraag vraag, Player player);
}

