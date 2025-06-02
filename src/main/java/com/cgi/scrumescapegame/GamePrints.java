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
        System.out.println("  ga omhoog/omlaag/links/rechts (vooruit/ achteruit) - Verplaats je naar de volgende kamer.");
        System.out.println("  gebruik item [nummer]  - Gebruik de opgegeven item (bv. 'gebruik item 1').");
        System.out.println("  start puzzel            - Start een puzzel in de huidige kamer.");
        System.out.println("  armor opbergen        - Berg je huidige armor op.");
        System.out.println("  wapen opbergen         - Berg je huidige wapen op.");
        System.out.println("  status                 - Toon je huidige status en locatie.");
        System.out.println("  kijk rond              - Krijg de beschrijving van de huidige kamer opnieuw.");
        System.out.println("  items                  - Toon een lijst van jouw items.");
        System.out.println("  opslaan                - Sla de gamegegevens op.");
        System.out.println("  help                   - Toon dit Help bericht.");
        System.out.println("  stop                   - Stop het spel.");
    }
}
