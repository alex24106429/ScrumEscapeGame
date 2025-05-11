package com.cgi.scrumescapegame;

// Context klasse
public class MonsterAction {
    private Obstacle obstacle;

    public MonsterAction(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public void setObstakel(Obstacle newObstakel) {
        this.obstacle = newObstakel;
    }

    public void attempt(Player p){
        obstacle.attempt(p);

        if(obstacle.getIsOvercome()){
            System.out.println("Helemaal Goed! Je hebt het monster verslagen");
        }else{
            System.out.println("Fout! Je verliest een hartje");
        }
    }
}