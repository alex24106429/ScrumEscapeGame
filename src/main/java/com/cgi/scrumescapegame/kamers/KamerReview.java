package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.*;
import com.cgi.scrumescapegame.obstacles.ReviewMonster;

public class KamerReview extends Room {

    public Obstacle monster = new ReviewMonster();

    public KamerReview(int roomX, int roomY) {
        super("Review Kamer", "Welkom in de Sprint Review kamer. Hier presenteer je het werkende product aan de stakeholders.", roomX, roomY);
    }

    @Override
    public void enterRoom(Player player) {
        super.enterRoom(player);
        ImagePrinter.printImage(monster.getImagepath());
        monster.getPuzzle().start();
    }
}