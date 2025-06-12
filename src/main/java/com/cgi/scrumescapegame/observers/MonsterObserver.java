package com.cgi.scrumescapegame.observers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Vraag;

public class MonsterObserver implements PuzzleObserver {
    Player player;

    public void update(boolean isCorrect, Vraag vraag, Player player){
        if(!isCorrect){
            // player.loseLife();
        }
    }
}
