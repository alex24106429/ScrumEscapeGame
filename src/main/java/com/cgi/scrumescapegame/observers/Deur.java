package com.cgi.scrumescapegame.observers;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.animations.DoorOpeningAnimation;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;
import com.cgi.scrumescapegame.Player;

public class Deur implements PuzzleObserver {

    public void update(boolean isCorrect, Vraag vraag, Player player) {
        if (isCorrect) {
            DoorOpeningAnimation.playAnimation();
            PrintMethods.printlnColor("De deuren in de kamer zijn geopend!", Attribute.BRIGHT_GREEN_TEXT());
            player.getCurrentRoom().setCleared(true);
        }
        else {
            // Dit runnen wij nooit...
            // System.out.println("De deuren blijven gesloten.");
        }
    }
}
