package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.animations.TitleScreenAnimation;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.graphics.WallpaperHandler;
import com.diogonunes.jcolor.Attribute;

public class GamePrints {
    public static void printWelcome() {
        TitleScreenAnimation.playAnimation();
        PrintMethods.clearScreen();
        System.out.println("===================================");
        PrintMethods.printlnColor("     Welkom bij Scrum Escape!", Attribute.BRIGHT_YELLOW_TEXT());
        System.out.println("===================================");
    }

    public static void printHelp() {
        PrintMethods.printlnColor("Beschikbare commando's:", Attribute.BOLD());
        System.out.println("  ga (vooruit/achteruit/links/rechts) - Verplaats je naar de volgende kamer.");
        System.out.println("  gebruik item [nummer]  - Gebruik de opgegeven item (bv. 'gebruik item 1').");
        System.out.println("  start puzzel           - Start een puzzel in de huidige kamer.");
        System.out.println("  armor opbergen         - Berg je huidige armor op.");
        System.out.println("  wapen opbergen         - Berg je huidige wapen op.");
        System.out.println("  status                 - Toon je huidige status en locatie.");
        System.out.println("  map                    - Laat de map zien.");
        System.out.println("  kijk rond              - Krijg de beschrijving van de huidige kamer opnieuw.");
        System.out.println("  gebruik assistent      - Vraag de assistent om een hint, educatief hulpmiddel en motiverende boodschap.");
        System.out.println("  items                  - Toon een lijst van jouw items.");
        if(!Game.isScrumOS) System.out.println("  opslaan                - Sla de gamegegevens op.");
        System.out.println("  help                   - Toon dit Help bericht.");
        System.out.println("  stop                   - Stop het spel.");
    }

    public static void printGameEnd(Player player) {
        PrintMethods.clearScreen();
        WallpaperHandler.setWallpaper("freedom");

        PrintMethods.typeTextColor("\nMet de val van Miss Alignment voel je een schok door de kerker gaan.", Attribute.BRIGHT_CYAN_TEXT());
        PrintMethods.typeTextColor("De corrupte energie die de muren bijeenhield, lost op als mist in de ochtendzon.", Attribute.BRIGHT_CYAN_TEXT());
        Game.pause(1000);
        PrintMethods.typeTextColor("De stenen onder je voeten worden zacht en de geur van vergeten code maakt plaats voor frisse lucht.", Attribute.BRIGHT_CYAN_TEXT());
        Game.pause(1000);
        PrintMethods.typeTextColor("\nEen etherische stem, warm en dankbaar, vult de ruimte. Het is de Architect.", Attribute.BRIGHT_YELLOW_TEXT());
        PrintMethods.typeTextColor("'Dank je, " + player.getName() + ". Je hebt de principes van Scrum niet alleen begrepen, maar ook belichaamd.'", Attribute.BRIGHT_YELLOW_TEXT(), Attribute.ITALIC());
        PrintMethods.typeTextColor("'Je hebt de harmonie hersteld. De Scrum Dungeon is niet langer een gevangenis, maar een bron van kennis.'", Attribute.BRIGHT_YELLOW_TEXT(), Attribute.ITALIC());
        Game.pause(1000);
        PrintMethods.typeTextColor("\nVoor je verschijnt een helder, wit licht. Een uitgang.", Attribute.BRIGHT_WHITE_TEXT());
        PrintMethods.typeTextColor("Je hebt de Scrum Escape Game voltooid!", Attribute.BRIGHT_GREEN_TEXT(), Attribute.BOLD());

        System.out.println("\n\n===================================");
        PrintMethods.printlnColor("          EINDSTAND", Attribute.BOLD());
        System.out.println("===================================");
        PrintMethods.printlnColor("  Speler: " + player.getName(), Attribute.CYAN_TEXT());
        PrintMethods.printlnColor("  Eindtijd: " + Game.timer.getTimeSinceStartString(), Attribute.CYAN_TEXT());
        PrintMethods.printlnColor("  Level: " + player.getLevel(), Attribute.CYAN_TEXT());
        System.out.println("===================================");

        PrintMethods.typeTextColor("\nBedankt voor het spelen!", Attribute.BRIGHT_MAGENTA_TEXT());
        PrintMethods.typeTextColor("Druk op Enter om het spel af te sluiten...", Attribute.DIM());
        Game.scanner.nextLine();
        Game.quitGame();
    }
}