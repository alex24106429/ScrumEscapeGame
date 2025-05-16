package com.cgi.scrumescapegame;

import java.util.*;

import com.cgi.scrumescapegame.kamers.KamerPlanning;
import com.cgi.scrumescapegame.kamers.KamerRetrospective;
import com.diogonunes.jcolor.Attribute;
import com.google.gson.Gson;

import com.cgi.scrumescapegame.kamers.KamerReview;
import com.cgi.scrumescapegame.kamers.StartKamer;

public class Game {
    public final static Gson gson = new Gson();

    public final static DatabaseManager db = new DatabaseManager();

    private final Player player;
    private final List<Room> rooms;
    public final static Scanner scanner = new Scanner(System.in);
    private final Map map;
    public static final boolean debug = true; // Zet dit op false voor de eindversie
    public static boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

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
        PrintMethods.clearScreen();
        printWelcome();
        map.generateMap();
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

        if(debug) printBeschikbareKamers();

        while (true) {
            // if(!scanner.hasNextLine()) continue;
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();
            InputProcessor.processInput(input, player, this, Game.scanner);
        }
    }

    public void saveGame() {
        PrintMethods.printlnColor("Gamegegevens opslaan...", Attribute.BRIGHT_YELLOW_TEXT());
        String createTableSql = "CREATE TABLE IF NOT EXISTS game_state (id INT PRIMARY KEY, current_room_index INT, player_lives INT)";
        db.executeQuery(createTableSql);
        String upsertSql = String.format("MERGE INTO game_state KEY(id) VALUES (1, %d, %d)",
                rooms.indexOf(player.getCurrentRoom()), player.getLives());
        db.executeQuery(upsertSql);
        PrintMethods.printlnColor("Opgeslagen!", Attribute.BRIGHT_GREEN_TEXT());
    }

    private void printWelcome() {
        ImagePrinter.printImage("logo.png");
        System.out.println("===================================");
        PrintMethods.printlnColor("     Welkom bij Scrum Escape!", Attribute.BRIGHT_YELLOW_TEXT());
        System.out.println("===================================");
    }

    void printBeschikbareKamers() {
        PrintMethods.printlnColor("\nBeschikbare kamers (voor 'ga naar kamer X'):", Attribute.BOLD());
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println("  Kamer " + (i + 1) + ": " + rooms.get(i).getName());
        }
    }

    void printHelp() {
        PrintMethods.printlnColor("Beschikbare commando's:", Attribute.BOLD());
        System.out.println("  ga naar kamer [nummer] - Verplaats naar de opgegeven kamer (bv. 'ga naar kamer 1').");
        System.out.println("  gebruik item [nummer]  - Gebruik de opgegeven item (bv. 'gebruik item 1').");
        System.out.println("  status                 - Toon je huidige status en locatie.");
        System.out.println("  kijk rond              - Krijg de beschrijving van de huidige kamer opnieuw.");
        System.out.println("  kamers                 - Toon een lijst van beschikbare kamers.");
        System.out.println("  items                  - Toon een lijst van jouw items.");
        System.out.println("  opslaan                - Sla de gamegegevens op.");
        System.out.println("  help                   - Toon dit Help bericht.");
        System.out.println("  quit                   - Stop het spel.");
    }

    void moveToRoom(int roomNumber) {
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