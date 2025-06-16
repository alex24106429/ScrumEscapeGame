package com.cgi.scrumescapegame.enemies;

import java.util.List;
import java.util.ArrayList;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemyattacks.AttackBehavior;
import com.cgi.scrumescapegame.graphics.PrintMethods;

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

    public int getCurrentHp() {
        return currentHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void changeHp(int amount) {
        this.currentHp += amount;
        if (this.currentHp < 0) {
            this.currentHp = 0;
        }
        if (this.currentHp > this.maxHp) {
            this.currentHp = this.maxHp;
        }
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    public String getHpString() {
        return PrintMethods.getProgressBarString((int) ((double) currentHp / maxHp * 100), 5) + " " + currentHp + "/" + maxHp;
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
