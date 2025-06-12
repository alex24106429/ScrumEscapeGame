package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class GoldenGun extends Item implements BattleItem, LimitedUseItem {
    @Override
    public String getName() {
        return "Gouden Pistool";
    }

    @Override
    public String getDescription() {
        return "Dit is het legendarische gouden pistool. Het schijnt dat het altijd raak is, maar je moet wel geluk hebben.";
    }

    @Override
    public String getImagepath() {
        return "items/goldengun.png";
    }

    @Override
    public void useBattleItem(Player player, Enemy enemy) {
        int roll = Randomizer.getRandomInt(10);

        switch (roll) {
            case 9:
                PrintMethods.printlnColor("Je schiet je gouden pistool en doet 999 schade aan " + enemy.getName() + "!", Attribute.BRIGHT_GREEN_TEXT());
                enemy.takeDamage(999);
                break;

            case 8:
                PrintMethods.printlnColor("Het gouden pistool glijd uit je handen en raakt je!", Attribute.BRIGHT_RED_TEXT());
                player.changeHp( - 1000000);
                break;

            default:
                PrintMethods.printlnColor("Je probeert het gouden pistool te gebruiken... Helaas is hij niet geladen.", Attribute.BRIGHT_BLUE_TEXT());
        }
    }

    @Override
    public int getUsesLeft() {
        return 0;
    }

    public GoldenGun() {
        super(100);
    }
}