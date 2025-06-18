package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

import java.util.List;

public class SprintSnipper_CrabbyCritique implements AttackBehavior {

    private static final List<String> CRITIQUES = List.of(
            "Lef dat je dit committed hebt.",
            "Interessante aanpak. Niet hoe ik het zou doen, maar... interessant.",
            "Ik zie wat je probeerde te doen.",
            "Werkt dit toevallig alleen op jouw machine?",
            "Dit kon ook in één regel code, maar oké.",
            "Veel succes voor degene die dit over een half jaar moet aanpassen.",
            "Ik neem aan dat hier tests voor geschreven zijn?",
            "Dit gaat productie breken, markeer mijn woorden.",
            "Is dit écht wat de Product Owner wilde?",
            "Dit lost het probleem niet echt op, hè?",
            "Ah, de 'het-is-vrijdagmiddag'-commit.",
            "Ik word hier een beetje kribbig van.",
            "De commit message is langer dan de code-wijziging zelf."
    );

    @Override
    public String getName() {
        return "Crabby Critique";
    }

    @Override
    public int attack(Enemy enemy, Player player) {
        String randomCritique = CRITIQUES.get(Randomizer.getRandomInt(CRITIQUES.size()));

        PrintMethods.typeTextColor("Sprint Snipper zegt: " + randomCritique, Attribute.BRIGHT_YELLOW_TEXT());

        // This attack deals no actual damage, only emotional damage.
        return 0;
    }
}