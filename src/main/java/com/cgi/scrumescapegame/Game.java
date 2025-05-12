package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.kamers.KamerPlanning;
import com.cgi.scrumescapegame.kamers.KamerReview;
import com.cgi.scrumescapegame.kamers.StartKamer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class Game {
    private final Player player;
    private final List<Room> rooms;
    private final Scanner scanner;
    private boolean isRunning;
    private final Map map;

    public Game() {
        this.player = new Player("Avonturier"); // Standaard naam, kan later gevraagd worden
        this.rooms = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.map = new Map();
        map.generateMapLayout();
        this.isRunning = true;
        initializeRooms();
    }

    private void initializeRooms() {
        // Kamer 0 (index 0, maar voor speler kamer 1)
        rooms.add(new StartKamer());
        // Kamer 1 (index 1, voor speler kamer 2)
        rooms.add(new KamerPlanning());
        // Kamer 2 (index 2, voor speler kamer 3)
        rooms.add(new KamerReview());
        // Voeg hier meer kamers toe
    }


    public void start() {
        printWelcome();
        map.generateMap();
        if (!rooms.isEmpty()) {
            player.setCurrentRoom(rooms.getFirst()); // Start in de Eerste Kamer
            player.getCurrentRoom().enterRoom(player); // Roep enterRoom aan voor de initiële kamer
        } else {
            System.out.println("Fout: Geen kamers gedefinieerd. Het spel kan niet starten.");
            isRunning = false;
        }

        while (isRunning) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();
            processInput(input);
        }
        System.out.println("Bedankt voor het spelen van Scrum Escape Game!");
        scanner.close();
    }

	public void printlnColor(String text, Attribute colorAttribute) {
		System.out.println(Ansi.colorize(text, colorAttribute));
	}

    private void printWelcome() {
        ImagePrinter.printImage("logo.png");
        System.out.println("===================================");
		printlnColor("Welkom bij Scrum Escape Game!", Attribute.BRIGHT_YELLOW_TEXT());
        System.out.println("===================================");
    }

    private void printHelp() {
        System.out.println("\nBeschikbare commando's:");
        System.out.println("  ga naar kamer [nummer] - Verplaats naar de opgegeven kamer (bv. 'ga naar kamer 1').");
        System.out.println("  status                 - Toon je huidige status en locatie.");
        System.out.println("  kijk rond              - Krijg de beschrijving van de huidige kamer opnieuw.");
        System.out.println("  help                   - Toon dit Help bericht.");
        System.out.println("  quit                   - Stop het spel.");
        System.out.println("\nBeschikbare kamers (voor 'ga naar kamer X'):");
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println("  Kamer " + (i + 1) + ": " + rooms.get(i).getName());
        }
    }

    private void processInput(String input) {
        if (input.startsWith("ga naar kamer ")) {
            try {
                String roomNumberStr = input.substring("ga naar kamer ".length()).trim();
                int roomNumber = Integer.parseInt(roomNumberStr);
                moveToRoom(roomNumber);
            } catch (NumberFormatException e) {
                System.out.println("Ongeldig kamernummer. Gebruik bijvoorbeeld 'ga naar kamer 1'.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Die kamer bestaat niet. Typ 'help' om beschikbare kamers te zien.");
            }
        } else if (input.equals("status")) {
            System.out.println(player.getStatus());
        } else if (input.equals("kijk rond")) {
            if (player.getCurrentRoom() != null) {
                player.getCurrentRoom().enterRoom(player);
            } else {
                System.out.println("Je bent nog nergens!");
            }
        } else if (input.equals("help")) {
            printHelp();
        } else if (input.equals("quit") || input.equals("stop")) {
            isRunning = false;
        } else {
            System.out.println("Onbekend commando. Typ 'help' voor een lijst met commando's.");
        }
    }

    private void moveToRoom(int roomNumber) {
        // Kamernummers zijn 1-based voor de speler, maar 0-based in de lijst
        int roomIndex = roomNumber - 1;
        if (roomIndex >= 0 && roomIndex < rooms.size()) {
            Room targetRoom = rooms.get(roomIndex);
            player.setCurrentRoom(targetRoom);
            targetRoom.enterRoom(player); // Roep enterRoom aan bij het betreden
        } else {
            System.out.println("Ongeldig kamernummer. Kamer " + roomNumber + " bestaat niet.");
            System.out.println("Beschikbare kamers zijn 1 t/m " + rooms.size() + ".");
        }
    }
}