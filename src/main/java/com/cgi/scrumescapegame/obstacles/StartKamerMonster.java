package com.cgi.scrumescapegame.obstacles;

import com.cgi.scrumescapegame.Obstacle;
import com.cgi.scrumescapegame.Player;

public class StartKamerMonster implements Obstacle {
    public boolean isOvercome;
    private String vraag;
    private String imagepath;

    public boolean getIsOvercome(){
        return isOvercome;
    }

    public void setIsOvercome(boolean isOvercome){
        this.isOvercome = isOvercome;
    }

    public void attempt(Player p){
        System.out.println("Monster is hier!");
    }

    public String getVraag(){
        return vraag;
    }

    public String getImagepath(){
        return imagepath;
    }
}
