package com.cgi.scrumescapegame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cgi.scrumescapegame.kamers.EindKamer;
import com.cgi.scrumescapegame.kamers.KamerDailyStandup;
import com.cgi.scrumescapegame.kamers.KamerPlanning;
import com.cgi.scrumescapegame.kamers.KamerRetrospective;
import com.cgi.scrumescapegame.kamers.KamerReview;
import com.cgi.scrumescapegame.kamers.KamerScrumboard;
import com.cgi.scrumescapegame.kamers.StartKamer;

public class GameMap {
    static final List<Point> positions = new ArrayList<>();
    private final int roomCount = 24;

    public void generateMapLayout() {
        positions.add(new Point(0, 0));

        Random rand = new Random();

        int targetRoomCount = roomCount + 1;

        while (positions.size() < targetRoomCount) {
            boolean roomAddedThisAttempt = false;

            while (!roomAddedThisAttempt) {
                int randomIndex = rand.nextInt(positions.size());
                Point baseRoom = positions.get(randomIndex);

                int newX = baseRoom.x;
                int newY = baseRoom.y;
                int direction = rand.nextInt(4);

                switch (direction) {
                    case 0 -> newX++;
                    case 1 -> newY++;
                    case 2 -> newX--;
                    case 3 -> newY--;
                }

                if (checkAddRoom(newX, newY)) {
                    positions.add(new Point(newX, newY));
                    roomAddedThisAttempt = true;

                }
            }
        }

        for (int i = 1; i < positions.size(); i++) {
            Point p = positions.get(i);
            if (Game.debug)
                System.out.println("Kamer " + (i) + " positie: (" + p.x + ", + " + p.y + ")");
        }
    }

    public void initializeRooms(List<Room> rooms) {
        rooms.add(new StartKamer(0, 0));

        Random rand = new Random();

        for (int i = 1; i < getPositions().size(); i++) {
            int x = getPositions().get(i).x;
            int y = getPositions().get(i).y;

            int roomType = rand.nextInt(5);
            switch (roomType) {
                case 0 -> rooms.add(new KamerDailyStandup(x, y));
                case 1 -> rooms.add(new KamerPlanning(x, y));
                case 2 -> rooms.add(new KamerRetrospective(x, y));
                case 3 -> rooms.add(new KamerReview(x, y));
                case 4 -> rooms.add(new KamerScrumboard(x, y));
            }
        }
        Room lastRoom = rooms.getLast();
        rooms.set(rooms.size() - 1, new EindKamer(lastRoom.roomX, lastRoom.roomY));

        insertAdjacentRoom(rooms);
    }

    private void insertAdjacentRoom(List<Room> rooms) {
        for (Room room : rooms) {
            java.util.Map<String, Boolean> status = getAdjacentRoomStatus(room.roomX, room.roomY);

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

    public boolean checkAddRoom(int x, int y) {
        if (x < -roomCount / 2 || x > roomCount / 2 || y < 1 || y > roomCount) {
            return false;
        }
        return !hasRoom(x, y);
    }

    public static boolean hasRoom(int x, int y) {
        for (Point p : positions) {
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
        return positions;
    }

    public java.util.Map<String, Boolean> getAdjacentRoomStatus(int x, int y) {
        java.util.Map<String, Boolean> adjacentStatus = new java.util.HashMap<>();

        if (Game.debug) System.out.println("Positions list size: " + positions.size());
        if (Game.debug) System.out.println("Positions list contents: " + positions);

        adjacentStatus.put("right", false);
        adjacentStatus.put("left", false);
        adjacentStatus.put("up", false);
        adjacentStatus.put("down", false);

        for (Point p : positions) {
            if (p.x == x + 1 && p.y == y) {
                adjacentStatus.put("right", hasRoom(x + 1, y));
            }
            if (p.x == x - 1 && p.y == y) {
                adjacentStatus.put("left", hasRoom(x - 1, y));
            }
            if (p.x == x && p.y == y + 1) {
                adjacentStatus.put("up", hasRoom(x, y + 1));
            }
            if (p.x == x && p.y == y - 1) {
                adjacentStatus.put("down", hasRoom(x, y - 1));
            }
        }

        return adjacentStatus;
    }
}