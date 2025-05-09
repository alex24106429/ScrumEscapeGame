package com.cgi.scrumescapegame.obstacles;

import com.cgi.scrumescapegame.Obstacle;
import com.cgi.scrumescapegame.Player;

public class StartKamerMonster implements Obstacle {
    public boolean isOvercome;

    public boolean getIsOvercome(){
        return isOvercome;
    }

    public void attempt(Player p){
        System.out.println("Monster is hier!");
    }
}
