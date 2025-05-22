package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.observers.MonsterObserver;
import com.cgi.scrumescapegame.observers.PuzzleObserver;

public class ScrumEscapeGame {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();

        PuzzleObserver monsterobserver = new MonsterObserver();
    }
}
