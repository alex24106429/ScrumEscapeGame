package com.cgi.scrumescapegame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.cgi.scrumescapegame.kamers.EindKamer;
import com.cgi.scrumescapegame.kamers.KamerDailyStandup;
import com.cgi.scrumescapegame.kamers.KamerPlanning;
import com.cgi.scrumescapegame.kamers.KamerRetrospective;
import com.cgi.scrumescapegame.kamers.KamerReview;
import com.cgi.scrumescapegame.kamers.KamerScrumboard;
import com.cgi.scrumescapegame.kamers.StartKamer;

public class GameMap {
    private final List<Point> positions = new ArrayList<>();
    private int roomCount;

    private static final int DIRECTION_EAST = 0;
    private static final int DIRECTION_SOUTH = 1;
    private static final int DIRECTION_WEST = 2;
    private static final int DIRECTION_NORTH = 3;

    private static final String KEY_RIGHT = "right";
    private static final String KEY_LEFT = "left";
    private static final String KEY_UP = "up";
    private static final String KEY_DOWN = "down";

    public void generateMapLayout(int loopCount) {
        this.positions.add(new Point(0, 0));
        roomCount = (loopCount * 5);
        int targetRoomCount = roomCount + 2;

        while (this.positions.size() < (targetRoomCount)) {
            boolean roomAddedThisAttempt = false;

            while (!roomAddedThisAttempt) {
                int randomIndex = Randomizer.getRandomInt(this.positions.size());
                Point baseRoom = this.positions.get(randomIndex);

                int newX = baseRoom.x;
                int newY = baseRoom.y;
                int direction = Randomizer.getRandomInt(4);

                switch (direction) {
                    case DIRECTION_EAST -> newX++;
                    case DIRECTION_SOUTH -> newY++;
                    case DIRECTION_WEST -> newX--;
                    case DIRECTION_NORTH -> newY--;
                }

                if (checkAddRoom(newX, newY)) {
                    this.positions.add(new Point(newX, newY));
                    roomAddedThisAttempt = true;
                }
            }
        }

        // for (int i = 1; i < this.positions.size(); i++) {
        //     Point p = this.positions.get(i);
        //     if (Game.debug) System.out.println("Kamer " + (i) + " positie: (" + p.x + ", + " + p.y + ")");
        // }
    }

    public void initializeRooms(List<Room> rooms) {
        List<Supplier<Room>> roomSuppliers = List.of(
                () -> new KamerDailyStandup(0, 0),
                () -> new KamerPlanning(0, 0),
                () -> new KamerRetrospective(0, 0),
                () -> new KamerReview(0, 0),
                () -> new KamerScrumboard(0, 0)
        );
        List<Supplier<Room>> shuffleSuppliers = new ArrayList<>(roomSuppliers);
        Collections.shuffle(shuffleSuppliers);

        rooms.add(new StartKamer(0, 0));

        for (int i = 1; i < getPositions().size() - 1; i++) {
            if (shuffleSuppliers.isEmpty()) {
                shuffleSuppliers = new ArrayList<>(roomSuppliers);
                Collections.shuffle(shuffleSuppliers);
            }

            Room room = shuffleSuppliers.removeFirst().get(); // Nieuwe instantie
            int x = getPositions().get(i).x;
            int y = getPositions().get(i).y;
            room.setCurrentPosition(x, y);
            rooms.add(room);
        }
        int lastX = getPositions().getLast().x;
        int lastY = getPositions().getLast().y;
        rooms.add(new EindKamer(lastX, lastY));

        insertAdjacentRoom(rooms);
    }

    private void insertAdjacentRoom(List<Room> rooms) {
        for (Room room : rooms) {
            Map<String, Boolean> status = getAdjacentRoomStatus(room.roomX, room.roomY);

            // if(Game.debug) System.out.println("Kamernummer: " + room.getName());
            // if(Game.debug) System.out.println(status);

            if (status.get(KEY_RIGHT)) {
                room.setAdjacentRoom(KEY_RIGHT, true);
            }
            if (status.get(KEY_LEFT)) {
                room.setAdjacentRoom(KEY_LEFT, true);
            }
            if (status.get(KEY_UP)) {
                room.setAdjacentRoom(KEY_UP, true);
            }
            if (status.get(KEY_DOWN)) {
                room.setAdjacentRoom(KEY_DOWN, true);
            }
        }
    }

    public boolean checkAddRoom(int x, int y) {
        return !hasRoom(x, y);
    }

    public boolean hasRoom(int x, int y) {
        for (Point p : this.positions) {
            if (p.x == x && p.y == y) {
                return true;
            }
        }
        return false;
    }

    public static Room getRoomAt(int x, int y, List<Room> rooms) {
        for (Room room : rooms) {
            if (room.roomX == x && room.roomY == y) {
                return room;
            }
        }
        return null;
    }

    public List<Point> getPositions() {
        return Collections.unmodifiableList(this.positions);
    }

    public Map<String, Boolean> getAdjacentRoomStatus(int x, int y) {
        Map<String, Boolean> adjacentStatus = new HashMap<>();

        // if (Game.debug) System.out.println("Checking adjacent for: (" + x + "," + y + ")");
        // if (Game.debug) System.out.println("Positions list size: " + this.positions.size());

        adjacentStatus.put(KEY_RIGHT, hasRoom(x + 1, y));
        adjacentStatus.put(KEY_LEFT, hasRoom(x - 1, y));
        adjacentStatus.put(KEY_UP, hasRoom(x, y + 1));
        adjacentStatus.put(KEY_DOWN, hasRoom(x, y - 1));

        return adjacentStatus;
    }
}