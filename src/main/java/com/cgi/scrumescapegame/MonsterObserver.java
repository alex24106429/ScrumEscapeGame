package com.cgi.scrumescapegame;

public class MonsterObserver implements PuzzleObserver {
    Player player;

    public void update(boolean isCorrect, Vraag vraag){
        if(!isCorrect){
            player.loseLife();
        }
    }
}
