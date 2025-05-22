package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class GamePrints {
    public static void printWelcome() {
        ImagePrinter.printImage("logo.png");
        System.out.println("===================================");
        PrintMethods.printlnColor("     Welkom bij Scrum Escape!", Attribute.BRIGHT_YELLOW_TEXT());
        System.out.println("===================================");
    }

    public static void printHelp() {
        PrintMethods.printlnColor("Beschikbare commando's:", Attribute.BOLD());
        System.out.println("  ga naar kamer [nummer] - Verplaats naar de opgegeven kamer (bv. 'ga naar kamer 1').");
        System.out.println("  gebruik item [nummer]  - Gebruik de opgegeven item (bv. 'gebruik item 1').");
        System.out.println("  unequip armor          - Unequipt je huidige armor.");
        System.out.println("  unequip weapon         - Unequipt je huidige wapen.");
        System.out.println("  status                 - Toon je huidige status en locatie.");
        System.out.println("  kijk rond              - Krijg de beschrijving van de huidige kamer opnieuw.");
        System.out.println("  items                  - Toon een lijst van jouw items.");
        System.out.println("  opslaan                - Sla de gamegegevens op.");
        System.out.println("  help                   - Toon dit Help bericht.");
        System.out.println("  quit                   - Stop het spel.");
    }
}
