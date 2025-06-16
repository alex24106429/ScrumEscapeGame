package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.hints.FunnyHintProvider;
import com.cgi.scrumescapegame.items.Key;
import com.cgi.scrumescapegame.Player;

import java.util.ArrayList;

public class AssistantService {
    static boolean keyAcquired = false;
    static ArrayList<String> boodschap = new ArrayList<>();
    private static String imagePath = "monsters/krab.png";


    public static void voegBoodschapToe(String nieuweBoodschap) {
        boodschap.add(nieuweBoodschap);
    }

    public static void activate(Player player) {
        // Toon plaatje van assistent

        // 1) Toon een hint
        FunnyHintProvider hintProv = new FunnyHintProvider();
        System.out.println("Hint: " + hintProv.getHint());

        // 2) Toon educatief hulpmiddel
        System.out.println("Stappenplan: Volg deze stappen om het Scrum-ritueel te begrijpen:");
        System.out.println("1. Begrijp de doelstelling");
        System.out.println("2. Voer de ceremonie uit");
        System.out.println("3. Bespreek verbeteringen");

        // 3) Voeg boodschappen toe
        voegBoodschapToe("Je bent sterker dan je denkt.");
        voegBoodschapToe("Elke val is een les.");
        voegBoodschapToe("De uitgang is dichterbij dan je denkt.");
        voegBoodschapToe("Je hebt dit al bijna gefikst!");
        voegBoodschapToe("Durf verder te gaan.");
        voegBoodschapToe("Schaduw vreest licht – en jij!");
        voegBoodschapToe("Rust is voor later.");
        voegBoodschapToe("Je zwaard is scherp, net als jij.");
        voegBoodschapToe("Monsters hebben ook angst... voor jou.");
        voegBoodschapToe("Je tempo is goed, held!");

        // 4) Toon motiverende boodschap
        int index = Randomizer.getRandomInt(0, boodschap.size() - 1);
        System.out.println("Motivatie: " + boodschap.get(index));

        // 5) Verkrijg key
        int randomInt = Randomizer.getRandomInt(1, 10);
        if(randomInt == 1 && !keyAcquired){
            keyAcquired = true;
            player.addItem(new Key());
        }
    }
}