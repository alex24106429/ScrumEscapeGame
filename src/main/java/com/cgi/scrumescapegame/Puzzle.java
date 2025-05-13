package com.cgi.scrumescapegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Puzzle {
    private final List<Vraag> vragen;
    private int score;

    public Puzzle() {
        this.vragen = new ArrayList<>();
        this.score = 0;
    }

    public void addQuestion(Vraag vraag) {
        this.vragen.add(vraag);
    }

    public int start() {
        if (vragen.isEmpty()) {
            System.out.println("De puzzle bevat nog geen vragen.");
            return 0;
        }

        Scanner scanner = Game.scanner;

        for (int i = 0; i < vragen.size(); i++) {
            Vraag huidigeVraag = vragen.get(i);
            System.out.println("\nVraag " + (i + 1) + " van " + vragen.size() + ":");
            huidigeVraag.toonVraag(); 

            String gebruikersAntwoord = scanner.nextLine();

            if (huidigeVraag.controleerAntwoord(gebruikersAntwoord)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Helaas, dat is niet correct.");
                System.out.println("Het juiste antwoord was: " + huidigeVraag.getCorrectAntwoord());
            }
            System.out.println("----------------------");
        }

        System.out.println("\nPuzzle voltooid!");
        System.out.println("Uw score: " + score + " van de " + vragen.size() + " vragen correct.");

        return score;
    }
}
