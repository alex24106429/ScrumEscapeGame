package com.cgi.scrumescapegame;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver(Boolean isCorrect);
}

