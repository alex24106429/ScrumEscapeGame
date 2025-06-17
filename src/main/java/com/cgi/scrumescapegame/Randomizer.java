package com.cgi.scrumescapegame;

public class Randomizer {
	public static int getRandomInt(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("max must be greater than or equal to min");
		}
		return min + (int) (Math.random() * (max - min + 1));
	}

	public static int getRandomInt(int max) {
		return (int) (Math.random() * max);
	}

	public static float getRandomFloat() {
		return (float) Math.random();
	}

	public static boolean getRandomBoolean() {
		return Math.random() > 0.5;
	}

	/**
	 * Returns a random integer between 0 (inclusive) and max (inclusive),
	 * with a strong bias towards 0.
	 * This is achieved by squaring the random number, which skews the distribution.
	 *
	 * @param max The maximum possible value.
	 * @return A random integer, more likely to be close to 0.
	 */
	public static int getWeightedRandomInt(int max) {
		// Using a default weight strength of 2.0 (quadratic bias)
		return getWeightedRandomInt(0, max, 2.0);
	}

	/**
	 * Returns a random integer between min (inclusive) and max (inclusive),
	 * with a strong bias towards the minimum value.
	 * This is achieved by squaring the random number, which skews the distribution.
	 *
	 * @param min The minimum possible value.
	 * @param max The maximum possible value.
	 * @return A random integer, more likely to be close to min.
	 */
	public static int getWeightedRandomInt(int min, int max) {
		// Using a default weight strength of 2.0 (quadratic bias)
		return getWeightedRandomInt(min, max, 2.0);
	}

	/**
	 * Returns a random integer between min (inclusive) and max (inclusive),
	 * with a customizable bias towards the minimum value.
	 *
	 * @param min            The minimum possible value.
	 * @param max            The maximum possible value.
	 * @param weightStrength The strength of the bias.
	 *                       - 1.0 gives a uniform (non-weighted) distribution.
	 *                       - > 1.0 gives a bias towards the minimum value (e.g.,
	 *                       2.0, 3.0).
	 *                       - < 1.0 gives a bias towards the maximum value (e.g.,
	 *                       0.5).
	 * @return A random integer with a weighted distribution.
	 */
	public static int getWeightedRandomInt(int min, int max, double weightStrength) {
		if (min > max) {
			throw new IllegalArgumentException("max must be greater than or equal to min");
		}
		if (weightStrength <= 0) {
			throw new IllegalArgumentException("Weight strength must be a positive number.");
		}

		// Generate a random number and apply the weighting function (power)
		double weightedRandom = Math.pow(Math.random(), weightStrength);

		// Scale the weighted random number to the desired integer range
		return min + (int) (weightedRandom * (max - min + 1));
	}
}
