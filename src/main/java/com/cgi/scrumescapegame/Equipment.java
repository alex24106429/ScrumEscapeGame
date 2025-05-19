package com.cgi.scrumescapegame;


import com.diogonunes.jcolor.Attribute;

public class Equipment {
    private Weapon weapon;
    private Armor armor;
    private Player player;

    public Equipment(Player player) {
        this.player = player;
    }

    public void equip(EquipableItem item) {
        if (item instanceof Weapon newWeapon) {
            unequipWeapon();
            weapon = newWeapon;
            weapon.equip(player);
        } else if (item instanceof Armor newArmor) {
            unequipArmor();
            armor = newArmor;
            armor.equip(player);
        }
        PrintMethods.printlnColor("Je equipped " + item.getName(), Attribute.BRIGHT_GREEN_TEXT());
    }

    public void unequipWeapon() {
        if (weapon != null) {
            weapon.unequip(player);
            player.getInventory().addItem(weapon);
            weapon = null;
        }
    }

    public void unequipArmor() {
        if (armor != null) {
            armor.unequip(player);
            player.getInventory().addItem(armor);
            armor = null;
        }
    }

    public Weapon getWeapon() { return weapon; }
    public Armor getArmor() { return armor; }
}

