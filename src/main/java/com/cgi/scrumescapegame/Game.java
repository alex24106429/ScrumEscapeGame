package com.cgi.scrumescapegame;

import java.util.*;

import com.cgi.scrumescapegame.graphics.MapPrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.items.Book;
import com.cgi.scrumescapegame.items.HintJoker;
import com.cgi.scrumescapegame.items.KeyJoker;
import com.cgi.scrumescapegame.items.Torch;
import com.diogonunes.jcolor.Attribute;

public class Game {
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
        clearAndWelcome();
        initializeFirstRoom();

        if (!debug) {
            handlePlayerSetup();
        }

        finalizeSetup();
        enterInitialRoom();
        giveStartingItems();
        MapPrinter.printMap(player, this.map);

        gameLoop();
    }

    private void clearAndWelcome() {
        PrintMethods.clearScreen();
        GamePrints.printWelcome();
    }

    private void initializeFirstRoom() {
        player.setCurrentRoom(rooms.getFirst());
    }

    private void handlePlayerSetup() {
        promptPlayerName();
        promptDifficulty();
        promptJoker();
    }

    private void promptPlayerName() {
        PrintMethods.printColor("Typ je naam in: ", Attribute.BRIGHT_YELLOW_TEXT());
        player.setName(scanner.nextLine());
    }

    private void promptDifficulty() {
        displayDifficultyMenu();
        int choice = readDifficultyChoice();
        applyDifficultyChoice(choice);
    }

    private void displayDifficultyMenu() {
        PrintMethods.printlnColor("Kies de moeilijkheid:",     Attribute.BRIGHT_YELLOW_TEXT());
        PrintMethods.printlnColor("1. Makkelijk",               new Attribute[]{Attribute.GREEN_TEXT(), Attribute.BOLD()});
        System.out.println("Begin met 100 HP, 20 ATK, 20 DEF, en 50 Goud.");
        PrintMethods.printlnColor("2. Normaal",                 new Attribute[]{Attribute.YELLOW_TEXT(), Attribute.BOLD()});
        System.out.println("Begin met 50 HP, 10 ATK en 10 DEF.");
        PrintMethods.printlnColor("3. Moeilijk",                new Attribute[]{Attribute.RED_TEXT(), Attribute.BOLD()});
        System.out.println("Begin met 30 HP, 0 ATK en 0 DEF. Geen hints tijdens vragen!");
    }

    private int readDifficultyChoice() {
        while (true) {
            PrintMethods.printColor("(1/2/3) > ", Attribute.BRIGHT_BLUE_TEXT());
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                PrintMethods.printlnColor(
                    "Ongeldige invoer, voer a.u.b. een getal in (1, 2 of 3).",
                    Attribute.BRIGHT_RED_TEXT()
                );
            }
        }
    }

    private void applyDifficultyChoice(int input) {
        switch (input) {
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
                // Dit hoort nooit te gebeuren
                PrintMethods.printlnColor(
                    "Ongeldige invoer, je gaat verder met de moeilijkheid Normaal.",
                    Attribute.BRIGHT_RED_TEXT()
                );
                break;
        }
    }

    private void promptJoker() {
        displayJokerMenu();
        int choice = readJokerChoice();
        applyJokerChoice(choice);
    }

    private void displayJokerMenu() {
        PrintMethods.printlnColor("Kies een joker:",   Attribute.BRIGHT_YELLOW_TEXT());
        PrintMethods.printlnColor("1. Hint joker",      Attribute.BOLD());
        System.out.println("   Geeft een hint, werkt in alle kamers.");
        PrintMethods.printlnColor("2. Sleutel joker",   Attribute.BOLD());
        System.out.println("   Geeft 1 sleutel waarmee je de deuren in de kamers Daily Scrum en Review kan openen.");
    }

    private int readJokerChoice() {
        while (true) {
            PrintMethods.printColor("(1/2) > ", Attribute.BRIGHT_BLUE_TEXT());
            String line = scanner.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v == 1 || v == 2) {
                    return v;
                } else {
                    PrintMethods.printlnColor(
                        "Ongeldige keuze, kies 1 of 2.",
                        Attribute.BRIGHT_RED_TEXT()
                    );
                }
            } catch (NumberFormatException e) {
                PrintMethods.printlnColor(
                    "Ongeldige invoer, voer a.u.b. een getal in (1 of 2).",
                    Attribute.BRIGHT_RED_TEXT()
                );
            }
        }
    }

    private void applyJokerChoice(int input) {
        if (input == 1) {
            player.addItem(new HintJoker());
        } else {
            player.addItem(new KeyJoker());
        }
    }

    private void finalizeSetup() {
        player.setDifficulty(this.currentDifficulty);
    }

    private void enterInitialRoom() {
        player.getCurrentRoom().enterRoom(player, this.currentDifficulty);
    }

    private void giveStartingItems() {
        player.addItem(new Book());
        if (Game.debug) {
            player.addItem(new Torch());
        }
    }

    private void gameLoop() {
        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();
            InputProcessor.processInput(
                input, player, this,
                Game.scanner, this.map, this.currentDifficulty
            );
        }
    }


    public static void quitGame() {
        System.exit(0);
    }
}