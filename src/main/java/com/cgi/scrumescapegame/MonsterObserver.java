package com.cgi.scrumescapegame;

public class MonsterObserver implements Observer{
    Player player;

    public void update(boolean isCorrect){
        if(!isCorrect){
            player.loseLife();
        }
    }
}
