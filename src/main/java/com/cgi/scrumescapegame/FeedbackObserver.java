package com.cgi.scrumescapegame;

public class FeedbackObserver implements Observer{
    public void update(boolean isCorrect){
        if(isCorrect){
            System.out.println("Correct!");
        }else{
            System.out.println("Helaas, dat is niet correct.");
        }
    }
}