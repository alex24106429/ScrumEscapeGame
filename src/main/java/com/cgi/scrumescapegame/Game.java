package com.cgi.scrumescapegame;

import java.util.*;

import com.cgi.scrumescapegame.graphics.MapPrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;
import com.google.gson.Gson;

public class Game {
    public final static Gson gson = new Gson();

    private final Player player;
    public static final List<Room> rooms = new ArrayList<>();
    public final static Scanner scanner = new Scanner(System.in);
    public final GameMap map; // Made map public
    public static final boolean debug = true; // Zet dit op false voor de eindversie
    public static boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

    public Game() {
        this.player = new Player();
        this.map = new GameMap();
        map.generateMapLayout();
        map.initializeRooms(rooms);
    }

    public void start() {
        PrintMethods.clearScreen();
        GamePrints.printWelcome();
        player.setCurrentRoom(rooms.getFirst());

        if(!debug) {
            PrintMethods.printColor("Kies een naam: ", Attribute.BRIGHT_YELLOW_TEXT());
            player.setName(scanner.nextLine());
        }

        player.getCurrentRoom().enterRoom(player); // Roep enterRoom aan voor de initiële kamer

        if (rooms.isEmpty()) {
            System.out.println("Fout: Geen kamers gedefinieerd. Het spel kan niet starten.");
            System.exit(1);
        }

        MapPrinter.printMap(player, this.map);

        while (true) {
            // if(!scanner.hasNextLine()) continue;
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();
            InputProcessor.processInput(input, player, this, Game.scanner, this.map);
        }
    }

    public void saveGame() {
        PrintMethods.printlnColor("Gamegegevens opslaan...", Attribute.BRIGHT_YELLOW_TEXT());
        // PrintMethods.printlnColor("Opgeslagen!", Attribute.BRIGHT_GREEN_TEXT());
    }
}