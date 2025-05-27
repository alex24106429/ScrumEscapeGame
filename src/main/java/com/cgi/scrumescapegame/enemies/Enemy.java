package com.cgi.scrumescapegame.enemies;

public abstract class Enemy {
    protected String name;
    protected String imagePath;
    protected int currentHp;
    protected int maxHp;
    protected int attackDamage;

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

    public boolean isAlive() {
        return currentHp > 0;
    }
}
