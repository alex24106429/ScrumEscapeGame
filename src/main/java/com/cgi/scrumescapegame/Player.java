package com.cgi.scrumescapegame;
import com.cgi.scrumescapegame.items.Book;
import com.cgi.scrumescapegame.items.HealingPotion;
import com.cgi.scrumescapegame.items.Shield;
import com.cgi.scrumescapegame.items.Sword;

public class Player {
    private String name = "Avonturier";
    private Room currentRoom;
    private int lives = 3;
    private int score = 0;
    private int attack = 10;
    private int defense = 10;

    private Inventory inventory = new Inventory(this);
    private Equipment equipment = new Equipment(this);

    public Player() {
        inventory.addItem(new HealingPotion());
        inventory.addItem(new Sword());
        inventory.addItem(new Shield());
        inventory.addItem(new Book());
    }

    // Getters/setters
    public String getName() { return name; }
    public void setName(String newName) { this.name = newName; }

    public Room getCurrentRoom() { return currentRoom; }
    public void setCurrentRoom(Room room) { this.currentRoom = room; }

    public int getLives() { return lives; }
    public int getScore() { return score; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }

    public void addAttackModifier(int amount) { attack += amount; }
    public void addDefenseModifier(int amount) { defense += amount; }

    public void loseLife() {
        if (lives > 0) {
            lives--;
            PlayerPrinter.printLifeLost(this);
        } else {
            PlayerPrinter.printGameOver();
        }
    }

    public void gainLife() {
        if (lives < 3) {
            lives++;
            PlayerPrinter.printLifeGained(this);
        } else {
            PlayerPrinter.printMaxLives();
        }
    }

    public void changeScore(int amount) {
        score += amount;
    }

    public Inventory getInventory() { return inventory; }
    public Equipment getEquipment() { return equipment; }
}
