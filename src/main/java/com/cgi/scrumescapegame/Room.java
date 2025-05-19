package com.cgi.scrumescapegame;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public abstract class Room {
    protected String name;
    protected String description;
    protected int roomX;
    protected int roomY;
    protected Map<String, Boolean> adjacentRooms;

    public Room(String name, String description, int roomX, int roomY) {
        this.name = name;
        this.description = description;
        this.roomX = roomX;
        this.roomY = roomY;
        this.adjacentRooms = new HashMap<>();
    }

    public Point getCurrentPosition() {
        return new Point(roomX, roomY);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setAdjacentRoom(String direction, boolean status) {
        adjacentRooms.put(direction, status);
    }

    // Retourneer de map van aangrenzende kamers, geen formatted string
    public Map<String, Boolean> getAdjacentRooms() {
        return adjacentRooms;
    }

    public abstract void enterRoom(Player player);
}
