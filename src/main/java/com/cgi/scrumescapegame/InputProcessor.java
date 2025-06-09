package com.cgi.scrumescapegame;

import java.awt.Point;
import java.util.Objects;
import java.util.Scanner;

import com.cgi.scrumescapegame.graphics.MapPrinter;

public class InputProcessor {
    @SuppressWarnings("unused")
    public static void processInput(String input, Player player, Game game, Scanner scanner, GameMap map, Difficulty difficulty) {
        if (input.startsWith("ga ")) {
            String direction = input.substring("ga ".length()).trim();
            Room currentRoom = player.getCurrentRoom();
            Point currentPos = currentRoom.getCurrentPosition();
            int targetX = currentPos.x;
            int targetY = currentPos.y;
            boolean moved = false;

            if (!currentRoom.getCleared()){
                System.out.println("Je moet eerst de kamer oplossen voordat je verder kunt gaan.");
                return;
            }

            switch (direction) {
                case "voor":
                case "vooruit":
                    if (currentRoom.adjacentRooms.getOrDefault("up", false)) {
                        targetY++;
                        moved = true;
                    } else {
                        System.out.println("Je kunt niet vooruit gaan vanaf hier.");
                    }
                    break;
                case "achter":
                case "achteruit":
                    if (currentRoom.adjacentRooms.getOrDefault("down", false)) {
                        targetY--;
                        moved = true;
                    } else {
                        System.out.println("Je kunt niet achteruit gaan vanaf hier.");
                    }
                    break;
                case "links":
                    if (currentRoom.adjacentRooms.getOrDefault("left", false)) {
                        targetX--;
                        moved = true;
                    } else {
                        System.out.println("Je kunt niet naar links gaan vanaf hier.");
                    }
                    break;
                case "rechts":
                    if (currentRoom.adjacentRooms.getOrDefault("right", false)) {
                        targetX++;
                        moved = true;
                    } else {
                        System.out.println("Je kunt niet naar rechts gaan vanaf hier.");
                    }
                    break;
                default:
                    System.out.println("Ongeldige richting. Gebruik 'ga ...' gevolgd door vooruit, achteruit, links of rechts.");
                    break;
            }

            if (moved) {
                Point targetPos = new Point(targetX, targetY);
                Room nextRoom = null;
                for (Room room : Game.rooms) {
                    if (Objects.equals(room.getCurrentPosition(), targetPos)) {
                        nextRoom = room;
                        break;
                    }
                }

                if (nextRoom != null) {
                    player.setCurrentRoom(nextRoom);
                    nextRoom.enterRoom(player, difficulty);
                    MapPrinter.printMap(player, map);
                } else {
                    System.out.println("Er is geen kamer in die richting.");
                }
            }

        } else if (input.startsWith("gebruik item ")) {
            try {
                String itemNumberStr = input.substring("gebruik item ".length()).trim();
                int itemNumber = Integer.parseInt(itemNumberStr);
                player.useItem(itemNumber - 1);
            } catch (NumberFormatException e) {
                System.out.println("Ongeldig itemnummer. Gebruik bijvoorbeeld 'gebruik item 1'.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Die item bestaat niet. Typ 'items' om beschikbare items te zien.");
            }
        } else if (input.equals("armor opbergen")) {
            player.unequipArmor();
        } else if (input.equals("wapen opbergen")) {
            player.unequipWeapon();
        } else if (input.equals("status")) {
            player.printStatus();
        } else if (input.equals("kijk rond")) {
            player.kijkRond();
        } else if (input.equals("start puzzel")){
            if(player.getCurrentRoom() instanceof PuzzleRooms) {
                if (player.getCurrentRoom().getCleared() && !Game.debug) {
                    System.out.println("Deze kamer is al opgelost.");
                    return;
                }
                ((PuzzleRooms) player.getCurrentRoom()).startPuzzle(player, difficulty);
            } else {
                System.out.println("Je kunt hier geen puzzel starten.");
            }
        } else if (input.equals("items")) {
            player.printItems();
        } else if (input.equals("help")) {
            GamePrints.printHelp();
        } else if (input.equals("stop")) {
            Game.quitGame();
        } else {
            System.out.println("Onbekend commando: \"" + input + "\". Typ 'help' voor een lijst met commando's.");
        }
    }
}
