package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.kamers.KamerPlanning;
import com.cgi.scrumescapegame.kamers.KamerReview;
import com.cgi.scrumescapegame.kamers.StartKamer;

import java.awt.Point;
import java.io.IOException;
import java.util.*;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Console;

public class Game {
    private final Player player;
    private final List<Room> rooms;
    public final static Scanner scanner = new Scanner(System.in);
    private final Map map;
    public static final boolean debug = true; // Zet dit op false voor de eindversie
    boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

    public Game() {
        this.player = new Player();
        this.rooms = new ArrayList<>();
        this.map = new Map();
        map.generateMapLayout();
        initializeRooms();
    }

    private void initializeRooms() {
        // Kamer 0 (index 0, maar voor speler kamer 1)
        rooms.add(new StartKamer(0, 0));
//        map.getPositions().remove(0);

        Random rand = new Random();
        for (int i = 1; i < map.getPositions().size(); i++) {
            int roomType = rand.nextInt(4);
            switch (roomType) {
                case 0 -> rooms.add(new KamerPlanning(map.getPositions().get(i).x, map.getPositions().get(i).y));
                case 1 -> rooms.add(new KamerReview(map.getPositions().get(i).x, map.getPositions().get(i).y));
                case 2 -> rooms.add(new KamerPlanning(map.getPositions().get(i).x, map.getPositions().get(i).y));
                case 3 -> rooms.add(new KamerReview(map.getPositions().get(i).x, map.getPositions().get(i).y));
            }
        }
        insertAdjacentRoom();
    }


    public void start() {
        clearScreen();
        printWelcome();
        map.generateMap();
        player.setCurrentRoom(rooms.getFirst());

        if(!debug) {
            printColor("Kies een naam: ", Attribute.BRIGHT_YELLOW_TEXT());
            player.setName(scanner.nextLine());
        }
        
        player.getCurrentRoom().enterRoom(player); // Roep enterRoom aan voor de initiële kamer

        if (rooms.isEmpty()) {
            System.out.println("Fout: Geen kamers gedefinieerd. Het spel kan niet starten.");
            System.exit(1);
        }

        while (true) {
            // if(!scanner.hasNextLine()) continue;
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();
            processInput(input);
        }
    }

    public void saveGame() {
        printlnColor("Gamegegevens opslaan...", Attribute.BRIGHT_YELLOW_TEXT());
        String jdbcUrl = "jdbc:h2:./scrumescapedb;USER=sa;PASSWORD=sa";
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl);
            stmt = conn.createStatement();
            String createTableSql = "CREATE TABLE IF NOT EXISTS game_state (id INT PRIMARY KEY, current_room_index INT, player_lives INT)";
            stmt.execute(createTableSql);
            String upsertSql = String.format("MERGE INTO game_state KEY(id) VALUES (1, %d, %d)",
                    rooms.indexOf(player.getCurrentRoom()), player.getLives());
            stmt.execute(upsertSql);
            printlnColor("Opgeslagen!", Attribute.BRIGHT_GREEN_TEXT());
        } catch (SQLException e) {
            System.err.println("Fout bij opslaan van gamegegevens: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearScreen() {
        if(debug) return;

        System.out.print("\033\143");

        try {
            if (isWindows) {
                // For Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            if (debug) System.err.println("Error clearing console: " + e.getMessage());
        }
    }

    public void printColor(String text, Attribute colorAttribute) {
        System.out.print(Ansi.colorize(text, colorAttribute));
    }

    public void printlnColor(String text, Attribute colorAttribute) {
        System.out.println(Ansi.colorize(text, colorAttribute));
    }

    private void printWelcome() {
        ImagePrinter.printImage("logo.png");
        System.out.println("===================================");
        printlnColor("     Welkom bij Scrum Escape!", Attribute.BRIGHT_YELLOW_TEXT());
        System.out.println("===================================");
    }

    private void printHelp() {
        System.out.println("\nBeschikbare commando's:");
        System.out.println("  ga naar kamer [nummer] - Verplaats naar de opgegeven kamer (bv. 'ga naar kamer 1').");
        System.out.println("  status                 - Toon je huidige status en locatie.");
        System.out.println("  kijk rond              - Krijg de beschrijving van de huidige kamer opnieuw.");
        System.out.println("  opslaan                - Sla de gamegegevens op.");
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
            printlnColor(player.getCurrentRoom().description, Attribute.BRIGHT_YELLOW_TEXT());
        } else if (input.equals("opslaan")) {
            saveGame();
        }
        else if (input.equals("help")) {
            printHelp();
        } else if (input.equals("quit")) {
            if (debug) System.exit(0);
            
            printlnColor("Wil je opslaan? ja/nee", Attribute.BRIGHT_RED_TEXT());
            String option = scanner.nextLine();
            if (option.equals("ja")) {
                saveGame();
            }
            System.exit(0);
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
            targetRoom.enterRoom(player);
        } else {
            System.out.println("Ongeldig kamernummer. Kamer " + roomNumber + " bestaat niet.");
            System.out.println("Beschikbare kamers zijn 1 t/m " + rooms.size() + ".");
        }
    }
    private void insertAdjacentRoom() {
        for (Room room : rooms) {
            java.util.Map<String, Boolean> status = map.getAdjacentRoomStatus(room.roomX, room.roomY);

            if(Game.debug) System.out.println("Kamernummer: " + room.getName());
            if(Game.debug) System.out.println(status);
            // Set adjacent rooms based on the status
            if (status.get("right")) {
                room.setAdjacentRoom("right", true);
            }
            if (status.get("left")) {
                room.setAdjacentRoom("left", true);
            }
            if (status.get("up")) {
                room.setAdjacentRoom("up", true);
            }
            if (status.get("down")) {
                room.setAdjacentRoom("down", true);
            }
        }
    }

}