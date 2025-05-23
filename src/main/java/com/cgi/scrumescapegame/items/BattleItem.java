package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.Enemy;

public interface BattleItem {
    public abstract void useBattleItem(Player player, Enemy enemy);
}