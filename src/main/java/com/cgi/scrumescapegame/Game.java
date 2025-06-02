package com.cgi.scrumescapegame;

import java.util.*;

import com.cgi.scrumescapegame.graphics.MapPrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.items.BagOfGold;
import com.cgi.scrumescapegame.items.Book;
import com.cgi.scrumescapegame.items.Torch;
import com.diogonunes.jcolor.Attribute;
import com.google.gson.Gson;

public class Game {
    public final static Gson gson = new Gson();

    private final Player player;
    public static final List<Room> rooms = new ArrayList<>();
    private Difficulty currentDifficulty = Difficulty.NORMAL;

    public final static Scanner scanner = new Scanner(System.in);
    public final GameMap map;
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
            PrintMethods.printColor("Enter your name: ", Attribute.BRIGHT_YELLOW_TEXT());
            player.setName(scanner.nextLine());

            PrintMethods.printlnColor("Kies uw moeilijkheidsgraat:", Attribute.BRIGHT_BLUE_TEXT());
            PrintMethods.printlnColor("1. Makkelijk", new Attribute[]{Attribute.GREEN_TEXT(), Attribute.BOLD()});
            System.out.println("Begint met 100 HP, 20 ATK, 20 DEF, en 50 Goud.");
            PrintMethods.printlnColor("2. Normaal", new Attribute[]{Attribute.YELLOW_TEXT(), Attribute.BOLD()});
            System.out.println("Begint met 50 HP, 10 ATK en 10 DEF.");
            PrintMethods.printlnColor("3. Moeilijk", new Attribute[]{Attribute.RED_TEXT(), Attribute.BOLD()});
            System.out.println("Begint met 30 HP, 0 ATK en 0 DEF. Geen hints tijdens vragen!");

            PrintMethods.printColor("> ", Attribute.BRIGHT_BLUE_TEXT());

            int difficultyInput = scanner.nextInt();
            switch (difficultyInput) {
                case 1:
                    this.currentDifficulty = Difficulty.EASY;
                    break;

                case 2:
                    this.currentDifficulty = Difficulty.NORMAL;
                    break;
                case 3:
                    this.currentDifficulty = Difficulty.HARD;
                    break;
            
                default:
                    PrintMethods.printlnColor("Ongeldige invoer, Je gaat verder met normaal. ", Attribute.BRIGHT_RED_TEXT());
                    break;
            }
            scanner.nextLine(); // om "Onbekend commando" te voorkomen
        }

        player.setDifficulty(currentDifficulty);

        player.getCurrentRoom().enterRoom(player, this.currentDifficulty); // Roep enterRoom aan voor de initiële kamer

        player.addItem(new Book());
        player.addItem(new BagOfGold());
        if (Game.debug) player.addItem(new Torch());

        if (rooms.isEmpty()) {
            System.out.println("Fout: Geen kamers gedefinieerd. Het spel kan niet starten.");
            System.exit(1);
        }

        MapPrinter.printMap(player, this.map);

        while (true) {
            // if(!scanner.hasNextLine()) continue;
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();
            InputProcessor.processInput(input, player, this, Game.scanner, this.map, this.currentDifficulty);
        }
    }

    public static void saveGame() {
        PrintMethods.printlnColor("Gamegegevens opslaan...", Attribute.BRIGHT_YELLOW_TEXT());
        // PrintMethods.printlnColor("Opgeslagen!", Attribute.BRIGHT_GREEN_TEXT());
    }

    @SuppressWarnings("unused")
    public static void quitGame(boolean promptSave) {
        if (Game.debug || !promptSave) System.exit(0);

        PrintMethods.printlnColor("Wil je opslaan? (y/n)", Attribute.BRIGHT_RED_TEXT());
        String option = scanner.nextLine();
        if (option.toLowerCase().startsWith("y")) {
            saveGame();
        }
        System.exit(0);
    }
}