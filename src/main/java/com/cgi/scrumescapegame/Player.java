package com.cgi.scrumescapegame;

import java.util.ArrayList;

import com.cgi.scrumescapegame.items.HealingPotion;
import com.cgi.scrumescapegame.items.Sword;
import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class Player {
    private String name = "Avonturier";
    private Room currentRoom;
    private int lives;
    private int attack;
    private int defense;
    private int score;
    private ArrayList<Item> items;
    private Weapon equippedWeapon;
    private Armor equippedArmor;

    public Player() {
        // Standaard gegevens
        this.lives = 3;
        this.score = 0;
        this.attack = 10;
        this.defense = 10;
        this.items = new ArrayList<>();
        addItem(new HealingPotion());
        addItem(new Sword());
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public int changeScore(int amount) {
        return this.score += amount;
    }

    public void printStatus() {
        String output = "";
        // Speler naam
        output += Ansi.colorize("[ Speler: " + Ansi.colorize(name, Attribute.BOLD()), Attribute.BRIGHT_CYAN_TEXT())
                + Ansi.colorize(" ] ", Attribute.BRIGHT_CYAN_TEXT());

        // Kamernaam
        output += Ansi.colorize("[ Locatie: " + Ansi.colorize(currentRoom.getName(), Attribute.BOLD()),
                Attribute.BRIGHT_BLUE_TEXT())
                + Ansi.colorize(" ] ", Attribute.BRIGHT_BLUE_TEXT());

        // Aantal levens
        output += Ansi.colorize("[ Levens: " + getLivesString() + " ] ", Attribute.BRIGHT_RED_TEXT());

        // Score
        output += Ansi.colorize("[ Score: " + Ansi.colorize("" + score, Attribute.BOLD()),
                Attribute.BRIGHT_YELLOW_TEXT())
                + Ansi.colorize(" ] ", Attribute.BRIGHT_YELLOW_TEXT());

        output += "\n";

        // Attack
        output += Ansi.colorize("[ ATK: " + Ansi.colorize("" + attack, Attribute.BOLD()),
                Attribute.BRIGHT_RED_TEXT())
                + Ansi.colorize(" ] ", Attribute.BRIGHT_RED_TEXT());
        
        // Defense
        output += Ansi.colorize("[ DEF: " + Ansi.colorize("" + defense, Attribute.BOLD()),
                Attribute.GREEN_TEXT())
                + Ansi.colorize(" ] ", Attribute.GREEN_TEXT());

        output += "\n";

        // Toegankelijke kamers
        output += Ansi.colorize("[ Toegankelijke Kamers: " + currentRoom.availableRooms() + " ] ",
                Attribute.BRIGHT_GREEN_TEXT());

        // Coordinaten (alleen in debug)
        if (Game.debug)
            output += Ansi
                    .colorize("[ Coordianten: " + Ansi.colorize(
                            currentRoom.getCurrentPosition().x + ", " + currentRoom.getCurrentPosition().y,
                            Attribute.BOLD()), Attribute.BRIGHT_CYAN_TEXT())
                    + Ansi.colorize(" ] ", Attribute.BRIGHT_CYAN_TEXT());

        System.out.println(output);
    }

    public String getLivesString() {
        String output = "";
        for (int i = 0; i < lives; i++) {
            output += "♥ ";
        }
        for (int i = lives; i < 3; i++) {
            output += "♡ ";
        }
        return output.trim();
    }

    public void loseLife() {
        if (lives > 0) {
            lives--;
            PrintMethods.printlnColor("Je hebt een leven verloren! Je hebt nog " + lives + " levens over.",
                    Attribute.BRIGHT_RED_TEXT());
            printStatus();
        } else {
            PrintMethods.printlnColor("Game over! Je hebt geen levens meer.", Attribute.BRIGHT_RED_TEXT());
        }
    }

    public void gainLife() {
        if (lives < 3) {
            lives++;
            PrintMethods.printlnColor("Je hebt een leven gewonnen! Je hebt nu " + lives + " levens.",
                    Attribute.BRIGHT_GREEN_TEXT());
        } else {
            PrintMethods.printlnColor("Je hebt al het maximale aantal levens.", Attribute.BRIGHT_GREEN_TEXT());
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void printItems() {
        PrintMethods.printlnColor("Je items:", Attribute.BOLD());
        if (equippedWeapon != null) {
            System.out.println("Je wapen: " + equippedWeapon.getName());
            PrintMethods.printlnColor(equippedWeapon.getDescription(), Attribute.BRIGHT_BLUE_TEXT());
            ImagePrinter.printImage(equippedWeapon.getImagepath());
        }
        if (equippedArmor != null) {
            System.out.println("Je armor: " + equippedArmor.getName());
            PrintMethods.printlnColor(equippedArmor.getDescription(), Attribute.BRIGHT_BLUE_TEXT());
            ImagePrinter.printImage(equippedArmor.getImagepath());
        }
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println((i + 1) + ": " + item.getName());
            PrintMethods.printlnColor(item.getDescription(), Attribute.BRIGHT_BLUE_TEXT());
            ImagePrinter.printImage(item.getImagepath());
        }
    }

    public void addItem(Item item) {
        PrintMethods.printlnColor("Je hebt de item " + item.getName() + " gekregen!", Attribute.BRIGHT_GREEN_TEXT());
        items.add(item);
    }

    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
        weapon.equip(this);
    }

    public void equipArmor(Armor armor) {
        this.equippedArmor = armor;
        armor.equip(this);
    }

    public void useItem(int itemIndex) {
        int index = itemIndex - 1;
        if (index >= 0 && index < items.size()) {
            Item item = items.get(index);
            if (item instanceof UsableItem) {
                ((UsableItem) item).useItem(this);
                items.remove(index);
            } else if (item instanceof EquipableItem) {
                if (item instanceof Weapon) {
                    equipWeapon((Weapon) item);
                } else if (item instanceof Armor) {
                    equipArmor((Armor) item);
                }
                PrintMethods.printlnColor("Je equipped " + item.getName(), Attribute.BRIGHT_GREEN_TEXT());
                items.remove(index);
            } else {
                PrintMethods.printlnColor("This item cannot be used or equipped.", Attribute.RED_TEXT());
            }
        } else {
            PrintMethods.printlnColor("Invalid item index.", Attribute.RED_TEXT());
        }
    }

    public void addAttackModifier(int amount) {
        this.attack += amount;
    }

    public void addDefenseModifier(int amount) {
        this.defense += amount;
    }
}