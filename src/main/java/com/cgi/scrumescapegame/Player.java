package com.cgi.scrumescapegame;

import java.util.ArrayList;

import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.items.Armor;
import com.cgi.scrumescapegame.items.BattleItem;
import com.cgi.scrumescapegame.items.Book;
import com.cgi.scrumescapegame.items.EquipableItem;
import com.cgi.scrumescapegame.items.Item;
import com.cgi.scrumescapegame.items.LimitedUseItem;
import com.cgi.scrumescapegame.items.Torch;
import com.cgi.scrumescapegame.items.UsableItem;
import com.cgi.scrumescapegame.items.Weapon;
import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class Player {
    private String name = "Avonturier";
    Room currentRoom;
    private int maxHp;
    private int currentHp;
    private int attack;
    private int defense;
    private int gold;
    private ArrayList<Item> items;
    private Weapon equippedWeapon;
    private Armor equippedArmor;

    public Player() {
        // Standaard gegevens
        this.maxHp = 50;
        this.currentHp = this.maxHp;
        this.gold = 0;
        this.attack = 10;
        this.defense = 10;
        this.items = new ArrayList<>();
        addItem(new Book());
        if (Game.debug) addItem(new Torch());
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

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public boolean isAlive() {
        return getCurrentHp() > 0;
    }

    public int getGold() {
        return gold;
    }

    public int changeGold(int amount) {
        if (amount > 0) {
            PrintMethods.printlnColor("You gained " + amount + " gold!", Attribute.BRIGHT_GREEN_TEXT());
        }
        if (amount < 0) {
            PrintMethods.printlnColor("You lost " + amount + " gold.", Attribute.BRIGHT_RED_TEXT());
        }
        return this.gold += amount;
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

        output += "\n";

        // HP
        output += Ansi.colorize("[ HP: " + getHpString() + " ] ", Attribute.BRIGHT_RED_TEXT());

        // Gold
        output += Ansi.colorize("[ Gold: " + Ansi.colorize("" + gold, Attribute.BOLD()),
                Attribute.BRIGHT_YELLOW_TEXT())
                + Ansi.colorize(" ] ", Attribute.BRIGHT_YELLOW_TEXT());

        // Attack
        output += Ansi.colorize("[ ATK: " + Ansi.colorize("" + attack, Attribute.BOLD()),
                Attribute.BRIGHT_RED_TEXT())
                + Ansi.colorize(" ] ", Attribute.BRIGHT_RED_TEXT());

        // Defense
        output += Ansi.colorize("[ DEF: " + Ansi.colorize("" + defense, Attribute.BOLD()),
                Attribute.GREEN_TEXT())
                + Ansi.colorize(" ] ", Attribute.GREEN_TEXT());

        // Coordinaten (alleen in debug)
        if (Game.debug)
            output += Ansi
                    .colorize("\n[ Coordianten: " + Ansi.colorize(
                            currentRoom.getCurrentPosition().x + ", " + currentRoom.getCurrentPosition().y,
                            Attribute.BOLD()), Attribute.BRIGHT_CYAN_TEXT())
                    + Ansi.colorize(" ] ", Attribute.BRIGHT_CYAN_TEXT());

        System.out.println(output);
    }

    public String getHpString() {
        return PrintMethods.getProgressBarString((int) ((double) currentHp / maxHp * 100), 5) + " " + currentHp + "/" + maxHp;
    }

    public void loseHp(int amount) {
        if (currentHp > 0) {
            currentHp -= amount;
            if(currentHp < 0) currentHp = 0;
            PrintMethods.printlnColor("You lost " + amount + " HP! Current HP: " + getHpString(),
                    Attribute.BRIGHT_RED_TEXT());
        } else {
            PrintMethods.printlnColor("Game over! You lost all HP.", Attribute.BRIGHT_RED_TEXT());
        }
    }

    public void gainHp(int amount) {
        if (currentHp < maxHp) {
            currentHp += amount;
            if(currentHp > maxHp) currentHp = maxHp;
            PrintMethods.printlnColor("You gained " + amount + " HP.",
                    Attribute.BRIGHT_GREEN_TEXT());
        } else {
            PrintMethods.printlnColor("You already have the maximum HP.", Attribute.BRIGHT_GREEN_TEXT());
        }
    }

    public Item getItem(int itemIndex) {
        return items.get(itemIndex);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Weapon getWeapon() {
        return this.equippedWeapon;
    }

    public String getWeaponName() {
        Weapon weapon = getWeapon();
        if(weapon == null) return "Your Fist";
        return weapon.getName();
    }

    public Armor getArmor() {
        return this.equippedArmor;
    }

    public void printItems() {
        if (equippedWeapon != null) {
            PrintMethods.printlnColor("Huidig wapen: ", Attribute.BOLD());
            PrintMethods.printItem(equippedWeapon);

        }
        if (equippedArmor != null) {
            PrintMethods.printlnColor("Huidig armor: ", Attribute.BOLD());
            PrintMethods.printItem(equippedArmor);
        }
        PrintMethods.printlnColor("\nJe items:", Attribute.BOLD());
        for (int i = 0; i < items.size(); i++) {
            System.out.print(String.format("%d. ", i + 1));
            PrintMethods.printItem(items.get(i));
        }
    }

    public void addItem(Item item) {
        PrintMethods.printlnColor("Je hebt de item " + item.getName() + " gekregen!", Attribute.BRIGHT_GREEN_TEXT());
        items.add(item);
    }

    public void removeItem(int itemIndex) {
        items.remove(itemIndex);
    }

    public void equipWeapon(Weapon weapon) {
        unequipWeapon();
        this.equippedWeapon = weapon;
        weapon.equip(this);
    }

    public void unequipWeapon() {
        if (this.equippedWeapon == null)
            return;
        this.equippedWeapon.unequip(this);
        items.add(this.equippedWeapon);
        this.equippedWeapon = null;
    }

    public void equipArmor(Armor armor) {
        unequipArmor();
        this.equippedArmor = armor;
        armor.equip(this);
    }

    public void unequipArmor() {
        if (this.equippedArmor == null)
            return;
        this.equippedArmor.unequip(this);
        items.add(this.equippedArmor);
        this.equippedArmor = null;
    }

    public void useItem(int index) {
        if (index >= 0 && index < items.size()) {
            Item item = items.get(index);
            if (item instanceof UsableItem) {
                ((UsableItem) item).useItem(this);
                if (item instanceof LimitedUseItem && ((LimitedUseItem) item).getUsesLeft() == 0)
                    items.remove(index);
            } else if (item instanceof EquipableItem) {
                if (item instanceof Weapon) {
                    equipWeapon((Weapon) item);
                } else if (item instanceof Armor) {
                    equipArmor((Armor) item);
                }
                PrintMethods.printlnColor("You equipped " + item.getName(), Attribute.BRIGHT_GREEN_TEXT());
                items.remove(index);
            } else {
                PrintMethods.printlnColor("This item cannot be used or equipped.", Attribute.RED_TEXT());
            }
        } else {
            PrintMethods.printlnColor("Invalid item index.", Attribute.RED_TEXT());
        }
    }

    public void useBattleItem(int index, Enemy enemy) {
        if (index >= 0 && index < items.size()) {
            Item item = items.get(index);
            if (item instanceof BattleItem) {
                ((BattleItem) item).useBattleItem(this, enemy);
            } else {
                PrintMethods.printlnColor("The item " + item.getName() + " is not a battle item.", Attribute.RED_TEXT());
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

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}