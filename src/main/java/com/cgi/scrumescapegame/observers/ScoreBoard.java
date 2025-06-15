// Dit moet herschreven worden om met XP te werken, aangezien puzzles geen score hebben

package com.cgi.scrumescapegame.observers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.vragen.Vraag;

public class ScoreBoard implements PuzzleObserver {
    // private int score;
    // private int highScore;

    public ScoreBoard() {
        // this.score = 0;
        // this.highScore = 0;
    }
    public void update(boolean isCorrect, Vraag vraag, Player player) {
        // if (isCorrect) {
        //     score += 10;
        //     System.out.println("Correct! Score: " + score);
        // }
        // if (score > highScore) {
        //     highScore = score;
        // }
    }

    public int getScore() {
        return 0;
    }

    public int getHighScore() {
        return 0;
    }
}
