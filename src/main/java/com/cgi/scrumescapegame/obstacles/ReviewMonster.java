package com.cgi.scrumescapegame.obstacles;

import com.cgi.scrumescapegame.MonsterAction;
import com.cgi.scrumescapegame.Obstacle;
import com.cgi.scrumescapegame.Player;

public class ReviewMonster implements Obstacle {
    public boolean isOvercome;
    private String vraag;
    private String imagepath;

    public ReviewMonster(String imagepath, String vraag){
        this.imagepath = imagepath;
        this.vraag = vraag;
    }

    public boolean getIsOvercome(){
        return isOvercome;
    }

    public void setIsOvercome(boolean isOvercome){
        this.isOvercome = isOvercome;
    }

    public String getImagepath(){
        return imagepath;
    }

    public String getVraag(){
        return vraag;
    }

    public void attempt(Player p){
        System.out.println("ReviewMonster is hier!");
    }
}