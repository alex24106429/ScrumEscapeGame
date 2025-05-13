package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.*;
import com.cgi.scrumescapegame.obstacles.ReviewMonster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KamerReview extends Room {
    private Obstacle rMonster;
    private MonsterAction actie;

    Obstacle vis = new ReviewMonster("fish.png", "Vraag van de vis");
    Obstacle kat = new ReviewMonster("cat_top_half.png", "Vraag van de kat");

    List<Obstacle> reviewmonsters = new ArrayList<>();

    public KamerReview(int roomX, int roomY) {
        super("Review Kamer", "Welkom in de Sprint Review kamer. Hier presenteer je het werkende product aan de stakeholders.", roomX, roomY);

        reviewmonsters.add(vis);
        reviewmonsters.add(kat);

        int rNummer = (int) (Math.random() * 2);
        rMonster = reviewmonsters.get(rNummer);

        actie = new MonsterAction(rMonster);
    }

    @Override
    public void enterRoom(Player player) {
        super.enterRoom(player);

        String[] texts = {
                "Je zwemt in feedback!",
                rMonster.getVraag()
        };

        // Specifieke acties voor de review kamer
        createSpeechBubble(texts);
		ImagePrinter.printImage(rMonster.getImagepath());
        actie.attempt(player);
    }
}