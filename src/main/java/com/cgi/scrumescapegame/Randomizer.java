package com.cgi.scrumescapegame;

public class Randomizer {
	public static int getRandomInt(int min, int max) {
		return min + (int)(Math.random() * (max - min + 1));
	}

	public static int getRandomInt(int max) {
		return (int)(Math.random() * max);
	}

	public static float getRandomFloat() {
		return (float) Math.random();
	}

	public static boolean getRandomBoolean() {
		return Math.random() > 0.5;
	}
}
