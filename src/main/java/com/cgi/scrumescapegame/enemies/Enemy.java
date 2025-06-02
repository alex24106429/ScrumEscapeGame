package com.cgi.scrumescapegame.enemies;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import com.cgi.scrumescapegame.Player;

public abstract class Enemy {
    protected String name;
    protected String imagePath;
    protected int currentHp;
    protected int maxHp;
    protected int attackDamage;
    protected List<AttackBehavior> behaviors = new ArrayList<>();
    protected EnemyState state = EnemyState.HEALTHY;
    protected Random rand = new Random();
    protected AttackBehavior lastBehavior;

    public Enemy(String name, String imagePath, int maxHp, int attackDamage) {
        this.name = name;
        this.imagePath = imagePath;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attackDamage = attackDamage;
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

    public int getAttack() {
        return attackDamage;
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
        state = currentHp < maxHp * 0.3 ? EnemyState.ENRAGED : EnemyState.HEALTHY;
        if (state == EnemyState.ENRAGED) {
            return rand.nextInt(3) == 0 ? behaviors.get(1) : behaviors.get(0);
        }
        return behaviors.get(0);
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
