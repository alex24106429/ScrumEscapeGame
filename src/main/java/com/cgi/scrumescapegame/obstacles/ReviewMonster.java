package com.cgi.scrumescapegame.obstacles;

import com.cgi.scrumescapegame.Obstacle;
import com.cgi.scrumescapegame.Player;

public class ReviewMonster implements Obstacle {
    public boolean isOvercome;

    public boolean getIsOvercome(){
        return isOvercome;
    }

    public void attempt(Player p){
        System.out.println("ReviewMonster is hier!");
    }
}