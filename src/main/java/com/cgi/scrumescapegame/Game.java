package com.cgi.scrumescapegame;

import java.util.*;

import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.kamers.KamerPlanning;
import com.cgi.scrumescapegame.kamers.KamerRetrospective;
import com.diogonunes.jcolor.Attribute;
import com.google.gson.Gson;

import com.cgi.scrumescapegame.kamers.KamerReview;
import com.cgi.scrumescapegame.kamers.StartKamer;
import com.cgi.scrumescapegame.observers.ObserverManager;

public class Game {
    public final static Gson gson = new Gson();

    private final Player player;
    public static final List<Room> rooms = new ArrayList<>();
    public final static Scanner scanner = new Scanner(System.in);
    private final Map map;
    public static final boolean debug = true; // Zet dit op false voor de eindversie
    public static boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

    public Game() {
        this.player = new Player();
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
        Puzzle puzzle = new Puzzle();
        ObserverManager observerManager = new ObserverManager();
        observerManager.startAllObservers(puzzle);
        System.out.println("buh");
        observerManager.pingObservers(puzzle, true);
    }


    public void start() {
        PrintMethods.clearScreen();
        GamePrints.printWelcome();
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

        if(debug) GamePrints.printBeschikbareKamers();

        while (true) {
            // if(!scanner.hasNextLine()) continue;
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();
            InputProcessor.processInput(input, player, this, Game.scanner);
        }
    }

    public void saveGame() {
        PrintMethods.printlnColor("Gamegegevens opslaan...", Attribute.BRIGHT_YELLOW_TEXT());
        // PrintMethods.printlnColor("Opgeslagen!", Attribute.BRIGHT_GREEN_TEXT());
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