package com.cgi.scrumescapegame.graphics;

public class FontConfig {
	public static Object[] getFontConfig(int fontIndex) {
        String filename;
        int size;
        int padding;
        switch (fontIndex) {
            case 1: filename = "/fonts/GravityRegular5.ttf"; size = 5; padding = 1; break;
            case 2: filename = "/fonts/hero-speak.ttf"; size = 14; padding = -3; break;
            case 3: filename = "/fonts/GravityBold8.ttf"; size = 8; padding = 0; break;
            case 4: filename = "/fonts/fibberish.ttf"; size = 16; padding = -7; break;
            case 5: filename = "/fonts/antiquity-print.ttf"; size = 13; padding = -1; break;
            default: throw new IllegalArgumentException("Invalid fontIndex: " + fontIndex + ". Must be between 1 and 5.");
        }
        return new Object[]{filename, size, padding};
    }
}
