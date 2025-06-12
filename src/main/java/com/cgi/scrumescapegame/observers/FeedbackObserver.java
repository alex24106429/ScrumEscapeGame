package com.cgi.scrumescapegame.observers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Vraag;

public class FeedbackObserver implements PuzzleObserver {
    private int correctAnswers;

    public FeedbackObserver(int correctAnswers){
        this.correctAnswers = correctAnswers;
    }

    @Override
    public void update(boolean isCorrect, Vraag vraag, Player player){
        if(isCorrect){
            System.out.println("Correct!");
            player.getCurrentRoom().setCleared(true);
        }else{
            System.out.println("Helaas, dat is niet correct.");
            System.out.println("Het juiste antwoord is: " + vraag.getCorrectAntwoord());
        }
    }
}