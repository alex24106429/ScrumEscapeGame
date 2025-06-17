package com.cgi.scrumescapegame;

import java.util.*;
import com.cgi.scrumescapegame.graphics.MapPrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.graphics.WallpaperHandler;
import com.cgi.scrumescapegame.items.*;
import com.cgi.scrumescapegame.kamers.Room;
import com.diogonunes.jcolor.Attribute;

public class Game {
    private final Player player;
    public static final List<Room> rooms = new ArrayList<>();
    private Difficulty currentDifficulty = Difficulty.NORMAL;
    public final static Timer timer = new Timer();

    public final static Scanner scanner = new Scanner(System.in);
    public final static Tutorial tutorial = new Tutorial();
    public final GameMap map;
    public static final boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
    public static final boolean isScrumOS = System.getProperty("user.name").equals("mainuser");

    public Game() {
        WallpaperHandler.setWallpaper("dungeon");
        this.player = new Player();
        this.map = new GameMap();
    }

    public void start() {
        clearAndWelcome();

        handlePlayerSetup();

        int jokerChoice = promptJoker();

        map.initializeRooms(rooms);
        timer.setStartTime();
        
        initializeFirstRoom();

        finalizeSetup();
        PrintMethods.clearScreen();
        enterInitialRoom();
        MapPrinter.printMap(player, this.map);
        applyJokerChoice(jokerChoice);
        player.addItemQuiet(new Book());
        tutorial.directionTutorial();

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
    }

    private void promptPlayerName() {
        String name;

        while(true) {
            PrintMethods.printColor("Typ je naam in: ", Attribute.BRIGHT_YELLOW_TEXT());
            String input = scanner.nextLine();
            int len = input.length();
            if(len < 2 || len > 15) {
                PrintMethods.printlnColor("Naam moet tussen 2 en 15 tekens lang zijn!", Attribute.BRIGHT_RED_TEXT());
                continue;
            }
            name = input;
            break;
        }
        player.setName(name);
    }

    private void promptDifficulty() {
        displayDifficultyMenu();
        int choice = readDifficultyChoice();
        applyDifficultyChoice(choice);
    }

    private void displayDifficultyMenu() {
        PrintMethods.printlnColor("Kies de moeilijkheid:", Attribute.BRIGHT_YELLOW_TEXT());
        PrintMethods.printlnColor("1. Makkelijk", Attribute.BRIGHT_GREEN_TEXT(), Attribute.BOLD());
        System.out.println("   Begin met 150 HP, 20 ATK, 20 DEF, en 50 Goud.");
        PrintMethods.printlnColor("2. Normaal", Attribute.BRIGHT_YELLOW_TEXT(), Attribute.BOLD());
        System.out.println("   Begin met 100 HP, 10 ATK en 10 DEF.");
        PrintMethods.printlnColor("3. Moeilijk", Attribute.BRIGHT_RED_TEXT(), Attribute.BOLD());
        System.out.println("   Begin met 50 HP, 5 ATK en 5 DEF. Geen hints tijdens vragen!");
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
                map.generateMapLayout(input);
                break;
            case 2:
                this.currentDifficulty = Difficulty.NORMAL;
                map.generateMapLayout(input);
                break;
            case 3:
                this.currentDifficulty = Difficulty.HARD;
                map.generateMapLayout(input);
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

    private int promptJoker() {
        displayJokerMenu();
        return readJokerChoice();
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
            player.addItemQuiet(new HintJoker());
        } else {
            player.hasChosenKeyJoker = true;
        }
    }

    private void finalizeSetup() {
        player.setDifficulty(this.currentDifficulty);
    }

    private void enterInitialRoom() {
        player.getCurrentRoom().enterRoom(player, this.currentDifficulty);
    }

    private void gameLoop() {
        while (true) {
            PrintMethods.printColor("\n> ", Attribute.BRIGHT_BLUE_TEXT());
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

    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    public static void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}