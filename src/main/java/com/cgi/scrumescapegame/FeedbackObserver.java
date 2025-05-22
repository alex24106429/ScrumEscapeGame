package com.cgi.scrumescapegame;

public class FeedbackObserver implements PuzzleObserver {
    private int correctAnswers;

    public FeedbackObserver(int correctAnswers){
        this.correctAnswers = correctAnswers;
    }

    @Override
    public void update(boolean isCorrect, Vraag vraag){
        if(isCorrect){
            System.out.println("Correct!");
            correctAnswers++;
        }else{
            System.out.println("Helaas, dat is niet correct.");
            System.out.println("Het juiste antwoord is: " + vraag.getCorrectAntwoord());
        }
    }
}