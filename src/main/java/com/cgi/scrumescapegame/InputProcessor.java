package com.cgi.scrumescapegame;

import java.util.Scanner;

import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class InputProcessor {
    public static void processInput(String input, Player player, Game game, Scanner scanner) {
        if (input.startsWith("ga naar kamer ")) {
            try {
                String roomNumberStr = input.substring("ga naar kamer ".length()).trim();
                int roomNumber = Integer.parseInt(roomNumberStr);
                game.moveToRoom(roomNumber);
            } catch (NumberFormatException e) {
                System.out.println("Ongeldig kamernummer. Gebruik bijvoorbeeld 'ga naar kamer 1'.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Die kamer bestaat niet. Typ 'kamers' om beschikbare kamers te zien.");
            }
        } else if (input.startsWith("gebruik item ")) {
            try {
                String itemNumberStr = input.substring("gebruik item ".length()).trim();
                int itemNumber = Integer.parseInt(itemNumberStr);
                player.useItem(itemNumber);
            } catch (NumberFormatException e) {
                System.out.println("Ongeldig itemnummer. Gebruik bijvoorbeeld 'gebruik item 1'.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Die item bestaat niet. Typ 'items' om beschikbare items te zien.");
            }
        } else if (input.equals("unequip armor")) {
            player.unequipArmor();
        } else if (input.equals("unequip weapon")) {
            player.unequipWeapon();
        } else if (input.equals("status")) {
            player.printStatus();
        } else if (input.equals("kijk rond")) {
            PrintMethods.printlnColor(player.getCurrentRoom().description, Attribute.BRIGHT_YELLOW_TEXT());
        } else if (input.equals("kamers")) {
            game.printBeschikbareKamers();
        } else if (input.equals("items")) {
            player.printItems();
        }else if (input.equals("opslaan")) {
            game.saveGame();
        }
        else if (input.equals("help")) {
            game.printHelp();
        } else if (input.equals("quit")) {
            if (Game.debug) System.exit(0);
            
            PrintMethods.printlnColor("Wil je opslaan? ja/nee", Attribute.BRIGHT_RED_TEXT());
            String option = scanner.nextLine();
            if (option.equals("ja")) {
                game.saveGame();
            }
            System.exit(0);
        } else {
            System.out.println("Onbekend commando. Typ 'help' voor een lijst met commando's.");
        }
    }    
}
