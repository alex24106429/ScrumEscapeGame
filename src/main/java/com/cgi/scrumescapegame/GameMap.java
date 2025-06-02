package com.cgi.scrumescapegame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
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
    private final List<Point> positions = new ArrayList<>();
    private final int roomCount = 24;

    private static final int DIRECTION_EAST = 0;
    private static final int DIRECTION_SOUTH = 1;
    private static final int DIRECTION_WEST = 2;
    private static final int DIRECTION_NORTH = 3;

    private static final String KEY_RIGHT = "right";
    private static final String KEY_LEFT = "left";
    private static final String KEY_UP = "up";
    private static final String KEY_DOWN = "down";

    private static final int ROOM_TYPE_DAILY_STANDUP = 0;
    private static final int ROOM_TYPE_PLANNING = 1;
    private static final int ROOM_TYPE_RETROSPECTIVE = 2;
    private static final int ROOM_TYPE_REVIEW = 3;
    private static final int ROOM_TYPE_SCRUMBOARD = 4;

    public void generateMapLayout() {
        this.positions.add(new Point(0, 0));

        Random rand = new Random();

        int targetRoomCount = roomCount + 1;

        while (this.positions.size() < targetRoomCount) {
            boolean roomAddedThisAttempt = false;

            while (!roomAddedThisAttempt) {
                int randomIndex = rand.nextInt(this.positions.size());
                Point baseRoom = this.positions.get(randomIndex);

                int newX = baseRoom.x;
                int newY = baseRoom.y;
                int direction = rand.nextInt(4);

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

        for (int i = 1; i < this.positions.size(); i++) {
            Point p = this.positions.get(i);
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
                case ROOM_TYPE_DAILY_STANDUP -> rooms.add(new KamerDailyStandup(x, y));
                case ROOM_TYPE_PLANNING -> rooms.add(new KamerPlanning(x, y));
                case ROOM_TYPE_RETROSPECTIVE -> rooms.add(new KamerRetrospective(x, y));
                case ROOM_TYPE_REVIEW -> rooms.add(new KamerReview(x, y));
                case ROOM_TYPE_SCRUMBOARD -> rooms.add(new KamerScrumboard(x, y));
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
        if (x < -roomCount / 2 || x > roomCount / 2 || y < 1 || y > roomCount) {
            return false;
        }
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

    public java.util.Map<String, Boolean> getAdjacentRoomStatus(int x, int y) {
        java.util.Map<String, Boolean> adjacentStatus = new java.util.HashMap<>();

        if (Game.debug) System.out.println("Scannen voor aanliggende kamers: (" + x + "," + y + ")");
        if (Game.debug) System.out.println("Positie lijst grootte: " + this.positions.size());

        adjacentStatus.put(KEY_RIGHT, hasRoom(x + 1, y));
        adjacentStatus.put(KEY_LEFT, hasRoom(x - 1, y));
        adjacentStatus.put(KEY_UP, hasRoom(x, y + 1));
        adjacentStatus.put(KEY_DOWN, hasRoom(x, y - 1));

        return adjacentStatus;
    }
}