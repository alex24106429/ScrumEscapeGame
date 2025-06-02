package com.cgi.scrumescapegame.enemies;

public class ReviewMonster extends Enemy {
    private static String name = "Review Monster";
    private static String imagePath = "monsters/reviewmonster.png";
    private static int maxHp = 50;
    private static int attackDamage = 10;
    
    public ReviewMonster() {
        super(name, imagePath, maxHp, attackDamage);
        behaviors.add(new NormalAttackBehavior());
        behaviors.add(new HeavyAttackBehavior());
    }
}
