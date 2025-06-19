package com.cgi.scrumescapegame.graphics;

public class FontConfig {
	public static Object[] getFontConfig() {
        String filename;
        int size;
        int padding;
        filename = "/fonts/hero-speak.ttf"; size = 14; padding = -3;
        return new Object[]{filename, size, padding};
    }
}
