package com.cgi.scrumescapegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.observers.PuzzleObserver;
import com.cgi.scrumescapegame.observers.PuzzleSubject;
import com.diogonunes.jcolor.Attribute;

public class Puzzle implements PuzzleSubject {
    private final List<Vraag> vragen;
    private final List<PuzzleObserver> observers;
    private Vraag currentVraag;

    public Puzzle() {
        this.vragen = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(PuzzleObserver observer){
        observers.add(observer);
    }

    @Override
    public void removeObserver(PuzzleObserver observer){
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(boolean isCorrect){
        for(PuzzleObserver observer : observers){
            observer.update(isCorrect, currentVraag);
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
            this.currentVraag = huidigeVraag;
            PrintMethods.printlnColor("Vraag " + (i + 1) + " van " + vragen.size() + ":", Attribute.BOLD());
            huidigeVraag.toonVraag();

            String gebruikersAntwoord = scanner.nextLine();

            boolean correct = huidigeVraag.controleerAntwoord(gebruikersAntwoord);
            if(Game.debug && gebruikersAntwoord.equals("skip")) correct = true;
            if (correct) {
                player.changeGold(10);
            } else {
                player.loseHp(10);
            }
            notifyObserver(correct);

            System.out.println("----------------------");
        }

        System.out.println("\nPuzzle voltooid!");
    }
}

