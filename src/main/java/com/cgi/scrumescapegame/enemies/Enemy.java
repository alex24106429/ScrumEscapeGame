package com.cgi.scrumescapegame.enemies;

import java.util.List;
import java.util.ArrayList;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;

public abstract class Enemy {
    protected String name;
    protected String imagePath;
    protected int currentHp;
    protected int maxHp;
    protected List<AttackBehavior> behaviors = new ArrayList<>();
    protected AttackBehavior lastBehavior;

    public Enemy(String name, String imagePath, int maxHp) {
        this.name = name;
        this.imagePath = imagePath;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getHealth() {
        return currentHp;
    }

    public void setHealth(int amount) {
        this.currentHp = amount;
    }

    public int getMaxHealth() {
        return maxHp;
    }

    public void takeDamage(int damage) {
        this.currentHp -= damage;
        if (this.currentHp < 0) {
            this.currentHp = 0;
        }
    }

    public void kill() {
        this.takeDamage(this.currentHp);
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    public AttackBehavior chooseBehavior() {
        return behaviors.get(Randomizer.getRandomInt(behaviors.size()));
    }

    public int performAttack(Player player) {
        AttackBehavior behavior = chooseBehavior();
        lastBehavior = behavior;
        return behavior.attack(this, player);
    }

    public String getLastActionName() {
        return lastBehavior.getName();
    }
}
