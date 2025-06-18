package com.cgi.scrumescapegame;

import java.time.Instant;
import java.util.ArrayList;

import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.items.*;
import com.cgi.scrumescapegame.kamers.Room;
import com.diogonunes.jcolor.Attribute;

public class Player {
    final int XP_PER_LEVEL = 100;

    private String name = "Avonturier";
    public Room currentRoom;
    private int maxHp;
    private int currentHp;
    private int attack;
    private int defense;
    private int gold;
    private final ArrayList<Item> items;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int level;
    private int experience;
    public boolean hasChosenKeyJoker = false;
    public boolean hasUsedKeyJoker = false;

    public Player() {
        this.gold = 0;
        this.items = new ArrayList<>();
        this.level = 1; // Start at level 1
        this.experience = 0; // Start with 0 experience
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

    public void changeGold(int amount) {
        if (amount > 0) {
            PrintMethods.printlnColor("Je hebt " + amount + " goud gekregen!", Attribute.BRIGHT_GREEN_TEXT());
        } else if (amount < 0) {
            PrintMethods.printlnColor("Je hebt " + Math.abs(amount) + " goud verloren.", Attribute.BRIGHT_RED_TEXT());
        }
    
        this.gold += amount;
    
        if (this.gold < 0) {
            this.gold = 0;
        }

    }

    public void kijkRond() {
        if (currentRoom.hasLookedAround()) {
            PrintMethods.printlnColor("Je hebt al rondgekeken in deze kamer.", Attribute.BRIGHT_RED_TEXT());
            return;
        }
        
        if(Randomizer.getRandomInt(3) == 0) {
            LootTable lootTable = new LootTable();
            Item loot = lootTable.roomLoot.get(Randomizer.getRandomInt(lootTable.roomLoot.size()));
            PrintMethods.typeTextColor("Je kijkt rond in de kamer en vindt een " + loot.getName() + "!", Attribute.BRIGHT_GREEN_TEXT());
            addItemQuiet(loot);
        } else {
            if(currentRoom.getCleared()) {
                PrintMethods.printlnColor("Je kijkt rond in de kamer en ziet open deuren.", Attribute.BRIGHT_YELLOW_TEXT());
            } else {
                PrintMethods.printlnColor("Je kijkt rond in de kamer en ziet gesloten deuren.", Attribute.BRIGHT_YELLOW_TEXT());
            }
        }
        currentRoom.setLookedAround(true);
    }

    public void printStatus() {
        PrintMethods.printColor("[ Speler: " + name + " ] ", "#FFD3B6");
        PrintMethods.printColor("[ Locatie: " + currentRoom.getName() + " ] ", "#D5ECC2");
        PrintMethods.printColor("[ Tijd: " + Game.timer.getTimeSinceStartString() + " ]\n", "#D5ECC2");
        PrintMethods.printColor("[ LVL: " + getLevel() + " ] ", "#95E1D3");
        PrintMethods.printColor("[ XP: " + getXPString() + " ] ", "#BE9FE1");
        PrintMethods.printColor("[ HP: " + getHpString() + " ] ", "#F38181");
        PrintMethods.printColor("[ Goud: " + getGold() + " ] ", "#FCE38A");
        PrintMethods.printColor("[ ATK: " + getAttack() + " ] ", "#E23E57");
        PrintMethods.printColor("[ DEF: " + getDefense() + " ]\n", "#A8D8EA");
    }

    public String getHpString() {
        return PrintMethods.getProgressBarString((int) ((double) currentHp / maxHp * 100), 5) + " " + currentHp + "/" + maxHp;
    }

     public String getXPString() {
        int xpToNextLevel = xpToNextLevel();
        return PrintMethods.getProgressBarString((int) ((double) experience / xpToNextLevel * 100), 5) + " " + experience + "/" + xpToNextLevel;
    }

    /**
     * Changes the player's current HP.
     * @param amount The amount to change HP by. Positive for gaining, negative for losing.
     * @return the amount of HP lost
     */
    public int changeHp(int amount) {
        if (amount == 0) return 0;

        // --- Gaining HP ---
        if (amount > 0) {
            if (currentHp >= maxHp) {
                PrintMethods.printlnColor("Je hebt al de maximale hoeveelheid HP.", Attribute.BRIGHT_GREEN_TEXT());
                return 0;
            }
            currentHp += amount;
            if (currentHp > maxHp) currentHp = maxHp;
            PrintMethods.printlnColor("Je hebt " + amount + " HP gekregen!", Attribute.BRIGHT_GREEN_TEXT());
            return 0;
        }
        // --- Losing HP ---
        // amount is negative
        int lossAmount = -amount - getDefense();
        if (lossAmount < 0) lossAmount = 0;
        if ((currentHp - lossAmount) > 0) {
            currentHp -= lossAmount;
            PrintMethods.printlnColor("Je verliest " + lossAmount + " HP! Huidige HP: " + getHpString(), Attribute.BRIGHT_RED_TEXT());
            return lossAmount;
        }
        // Game over logic
        PrintMethods.typeTextColor("Game over! Je hebt al je HP verloren.", Attribute.BRIGHT_RED_TEXT());
        PrintMethods.typeTextColor("Druk op Enter om door te gaan...", Attribute.BRIGHT_YELLOW_TEXT());
        Game.scanner.nextLine();
        Game.quitGame();
        return lossAmount;
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
        if(weapon == null) return "je vuist";
        return weapon.getName();
    }

    public Armor getArmor() {
        return this.equippedArmor;
    }

    public void printItems() {
        PrintMethods.printColor("Huidig wapen: ", Attribute.BOLD());
        if (equippedWeapon != null) {
            System.out.println();
            PrintMethods.printItem(equippedWeapon);
        } else {
            PrintMethods.printlnColor("geen", Attribute.BRIGHT_RED_TEXT());
        }
        PrintMethods.printColor("Huidig armor: ", Attribute.BOLD());
        if (equippedArmor != null) {
            System.out.println();
            PrintMethods.printItem(equippedArmor);
        } else {
            PrintMethods.printlnColor("geen", Attribute.BRIGHT_RED_TEXT());
        }
        PrintMethods.printlnColor("Je items:", Attribute.BOLD());
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d. ", i + 1);
            PrintMethods.printItem(items.get(i));
        }
    }

    public void addItem(Item item) {
        ImagePrinter.printImage(item.getImagepath());
        PrintMethods.typeTextColor("Je hebt de item " + item.getName() + " gekregen!", Attribute.BRIGHT_GREEN_TEXT());
        Game.tutorial.itemTutorial();
        if(item instanceof EquipableItem) Game.tutorial.equipableItemTutorial();
        items.add(item);
    }

    public void addItemQuiet(Item item) {
        items.add(item);
    }

    public void removeItem(int itemIndex) {
        items.remove(itemIndex);
    }

    public void removeItem(Item itemToRemove) {
        if (itemToRemove == this.equippedWeapon) {
            unequipItem(Weapon.class, false);
            return;
        }
        if (itemToRemove == this.equippedArmor) {
            unequipItem(Armor.class, false);
            return;
        }
        items.remove(itemToRemove);
    }

    public void equipItem(EquipableItem newItem) {
        if (newItem instanceof Weapon) {
            if (this.equippedWeapon != null) {
                this.equippedWeapon.unequip(this);
                items.add(this.equippedWeapon);
            }
            this.equippedWeapon = (Weapon) newItem;
        } else if (newItem instanceof Armor) {
            if (this.equippedArmor != null) {
                this.equippedArmor.unequip(this);
                items.add(this.equippedArmor);
            }
            this.equippedArmor = (Armor) newItem;
        } else {
            throw new IllegalArgumentException("Unsupported equipable item type");
        }

        newItem.equip(this);
    }


    public void unequipItem(Class<? extends EquipableItem> type, boolean addtoInventory) {
        if (type == Weapon.class && equippedWeapon != null) {
            equippedWeapon.unequip(this);
            if (addtoInventory) items.add(equippedWeapon);
            equippedWeapon = null;
        } else if (type == Armor.class && equippedArmor != null) {
            equippedArmor.unequip(this);
            if (addtoInventory) items.add(equippedArmor);
            equippedArmor = null;
        }
    }

    public void useItem(int index) {
        // Guard Clause: controleer op een ongeldige index en stop direct als dat zo is.
        if (index < 0 || index >= items.size()) {
            PrintMethods.printlnColor("Ongeldige item index.", Attribute.RED_TEXT());
            return;
        }

        Item item = items.get(index);

        // Handel het geval af waar het een 'UsableItem' is en stop daarna.
        if (item instanceof UsableItem) {
            ((UsableItem) item).useItem(this);
            if (item instanceof LimitedUseItem && ((LimitedUseItem) item).getUsesLeft() == 0) {
                items.remove(index);
            }
            return;
        }

        // Handel het geval af waar het een 'EquipableItem' is en stop daarna.
        if (item instanceof EquipableItem) {
            if (item instanceof Weapon) {
                equipItem((Weapon) item);
                PrintMethods.printlnColor(item.getName() + " is nu je huidige wapen. (+" + ((Weapon) item).getAttackBonus() + " ATK)",  Attribute.BRIGHT_YELLOW_TEXT());
            } else if (item instanceof Armor) {
                equipItem((Armor) item);
                PrintMethods.printlnColor(item.getName() + " is nu je huidige armor. (+" + ((Armor) item).getDefenseBonus() + " DEF)",  Attribute.BRIGHT_YELLOW_TEXT());
            }
            items.remove(index);
            return;
        }

        if (item instanceof BattleItem) {
            PrintMethods.printlnColor("Dit item kan alleen worden gebruikt tijdens een battle.", Attribute.RED_TEXT());
            return;
        }

        // Als geen van de bovenstaande gevallen van toepassing was, is het item niet bruikbaar.
        PrintMethods.printlnColor("Dit item kan niet worden gebruikt.", Attribute.RED_TEXT());
    }

    public void useBattleItem(int index, Enemy enemy) {
        if (index >= 0 && index < items.size()) {
            Item item = items.get(index);
            if (item instanceof BattleItem) {
                ((BattleItem) item).useBattleItem(this, enemy);
                if (item instanceof LimitedUseItem && ((LimitedUseItem) item).getUsesLeft() == 0) {
                    items.remove(index);
                }
            } else {
                PrintMethods.printlnColor("Het item " + item.getName() + " is geen gevechtsitem.", Attribute.RED_TEXT());
            }
        } else {
            PrintMethods.printlnColor("Ongeldige item index.", Attribute.RED_TEXT());
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

    public void setDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case Difficulty.EASY:
                this.maxHp = 150;
                this.currentHp = this.maxHp;
                this.attack = 15;
                this.defense = 15;
                this.gold = 100;
                break;

            case Difficulty.NORMAL:
                this.maxHp = 100;
                this.currentHp = this.maxHp;
                this.attack = 10;
                this.defense = 10;
                this.gold = 50;
                break;

            case Difficulty.HARD:
                this.maxHp = 50;
                this.currentHp = this.maxHp;
                this.attack = 5;
                this.defense = 5;
                break;

            default:
                break;
        }
    }

    public int getLevel() {
        return this.level;
    }

    public int getExperience() {
        return this.experience;
    }

    public void gainExperience(int amount) {
        if (amount < 1) return;

        this.experience += amount;
        PrintMethods.printlnColor("Je krijgt " + amount + " XP!", Attribute.BRIGHT_GREEN_TEXT());
        checkLevelUp(); // Check for level up after gaining XP
    }

    private void checkLevelUp() {
        // Calculate the potential new level based on total experience
        // Level 1 requires 0-499 XP, Level 2 requires 500-999 XP, etc.
        int newLevel = (this.experience / XP_PER_LEVEL) + 1;

        // If the calculated new level is greater than the current level, then level up
        if (newLevel > this.level) {
            int levelsGained = newLevel - this.level;
            this.level = newLevel;
            PrintMethods.printlnColor("Gefeliciteerd! Je bent nu level " + this.level + "!", Attribute.BRIGHT_GREEN_TEXT());
            
            // Apply bonuses for each level gained
            for (int i = 0; i < levelsGained; i++) {
                applyLevelUpBonuses();
            }

            printStatus();
        }
    }

    private void applyLevelUpBonuses() {
        this.maxHp += 10;
        this.currentHp = this.maxHp; 
        this.attack += 2;
        this.defense += 1;
    }

    private int xpToNextLevel() { 
        return XP_PER_LEVEL * level; 
    }

    public void saveData() {
        String output = "";
        output += "Name: " + getName() + "\n";
        output += "Current room: " + getCurrentRoom().getName() + "\n";
        output += "HP: " + getCurrentHp() + "\n";
        output += "Max HP: " + getMaxHp() + "\n";
        output += "ATK: " + getAttack() + "\n";
        output += "DEF: " + getDefense() + "\n";
        output += "Gold: " + getGold() + "\n";
        output += "LVL: " + getLevel() + "\n";
        output += "XP: " + getExperience() + "\n";
        FileHandler.writeFile(output, "save_" + Instant.now().toString().split("\\.")[0].replaceAll("\\:", "_") + ".txt");
    }
}