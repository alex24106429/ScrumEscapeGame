package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.obstacles.ReviewMonster;

// Context klasse
public class MonsterAction {
    private Obstacle obstacle;

    public MonsterAction(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public void setObstakel(Obstacle newObstakel) {
        this.obstacle = newObstakel;
    }

    public boolean attempt(Player p){
        obstacle.attempt(p);

        obstacle.setIsOvercome(true);

        if(obstacle.getIsOvercome()){
            System.out.println("Helemaal Goed! Je hebt het monster verslagen");
            return true;
        }else{
            System.out.println("Fout! Je verliest een hartje");
            return false;
        }
    }
}