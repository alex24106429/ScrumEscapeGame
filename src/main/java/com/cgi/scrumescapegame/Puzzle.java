package com.cgi.scrumescapegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.diogonunes.jcolor.Attribute;

public class Puzzle implements Subject{
    private final List<Vraag> vragen;
    private final List<Observer> observers;
    private int score;

    public Puzzle() {
        this.vragen = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.score = 0;
    }

    @Override
    public void registerObserver(Observer observer){
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(Boolean isCorrect){
        for(Observer observer : observers){
            observer.update(isCorrect);
        }
    }

    public void addQuestion(Vraag vraag) {
        this.vragen.add(vraag);
    }

    public void start(Player player) {
        if (vragen.isEmpty()) {
            System.out.println("De puzzle bevat nog geen vragen.");
            return;
        }

        Scanner scanner = Game.scanner;

        for (int i = 0; i < vragen.size(); i++) {
            Vraag huidigeVraag = vragen.get(i);
            PrintMethods.printlnColor("Vraag " + (i + 1) + " van " + vragen.size() + ":", Attribute.BOLD());
            huidigeVraag.toonVraag();

            String gebruikersAntwoord = scanner.nextLine();

            boolean correct = huidigeVraag.controleerAntwoord(gebruikersAntwoord);
            notifyObserver(correct);

            if (correct) {
                score++;
            } else {
                System.out.println("Het juiste antwoord was: " + huidigeVraag.getCorrectAntwoord());
            }
            System.out.println("----------------------");
        }

        System.out.println("\nPuzzle voltooid!");
        System.out.println("Uw score: " + score + " van de " + vragen.size() + " vragen correct.");

        player.changeScore(score * 10);
    }
}

