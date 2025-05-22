package com.cgi.scrumescapegame.items;

public abstract class Item {
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getImagepath();

    protected final int initialValue;

    public Item(int initialValue) {
        this.initialValue = initialValue;
    }

    public int getCurrentValue() {
        return initialValue;
    }
}