package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.hints.FunnyHintProvider;

public class AssistantService {
    public static void activate(Player player) {
        // 1) Toon een hint
        FunnyHintProvider hintProv = new FunnyHintProvider();
        System.out.println("Hint: " + hintProv.getHint());

        // 2) Toon educatief hulpmiddel
        System.out.println("Stappenplan: Volg deze stappen om het Scrum-ritueel te begrijpen:");
        System.out.println("1. Begrijp de doelstelling");
        System.out.println("2. Voer de ceremonie uit");
        System.out.println("3. Bespreek verbeteringen");

        // 3) Toon motiverende boodschap
        System.out.println("Je denkt als een echte product owner!");
    }
}