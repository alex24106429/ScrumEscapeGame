package com.cgi.scrumescapegame;

public interface PuzzleSubject {
    void registerObserver(PuzzleObserver observer);
    void removeObserver(PuzzleObserver observer);
    void notifyObserver(boolean isCorrect);
}

