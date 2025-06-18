package com.cgi.scrumescapegame;

public class Randomizer {
	/**
	 * Generates a random integer within a specified inclusive range.
	 * <p>
	 * This method ensures that the generated number will be greater than or equal to
	 * {@code min} and less than or equal to {@code max}.
	 *
	 * @param min The minimum value of the range (inclusive).
	 * @param max The maximum value of the range (inclusive).
	 * @return A random integer between min and max, inclusive.
	 * @throws IllegalArgumentException if max is less than min.
	 */
	public static int getRandomInt(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("max must be greater than or equal to min");
		}
		// Math.random() returns a double from 0.0 (inclusive) to 1.0 (exclusive).
		// (max - min + 1) calculates the size of the range.
		// The result is a random integer from min to max.
		return min + (int) (Math.random() * (max - min + 1));
	}

	/**
	 * Generates a random integer between 0 (inclusive) and a specified
	 * upper bound (exclusive).
	 * <p>
	 * This is an overload of {@link #getRandomInt(int, int)} with a default
	 * minimum value of 0. The behavior is similar to {@code java.util.Random.nextInt(int)}.
	 *
	 * @param max The upper bound for the generated random number (exclusive). Must be positive.
	 * @return A random integer between 0 (inclusive) and max (exclusive).
	 */
	public static int getRandomInt(int max) {
		// Math.random() * max results in a value from 0.0 to (max - 1).999...
		// Casting to int truncates the decimal, resulting in an integer from 0 to max-1.
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
		return getWeightedRandomInt(0, max, 3.0);
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
		return getWeightedRandomInt(min, max, 3.0);
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
