package com.cgi.scrumescapegame.graphics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import com.cgi.scrumescapegame.Game;

public class WallpaperHandler {
	/**
	 * Copies a classpath resource to a temp file and returns its path.
	 *
	 * @param resourcePath the classpath location (e.g. "data/config.yml")
	 * @return absolute path to the newly created temp file
	 * @throws IOException if the resource isn’t found or copy fails
	 */
	public static String copyResourceToTemp(String resourcePath) throws IOException {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		try (InputStream in = cl.getResourceAsStream(resourcePath)) {
			if (in == null) {
				throw new FileNotFoundException("Resource not found: " + resourcePath);
			}
			// derive a sensible prefix/suffix from the resource’s filename
			String name = Paths.get(resourcePath).getFileName().toString();
			String prefix = name, suffix = "";
			int dot = name.lastIndexOf('.');
			if (dot > 0) {
				prefix = name.substring(0, dot);
				suffix = name.substring(dot);
			}
			if (prefix.length() < 3)
				prefix = "tmp"; // File.createTempFile requires >=3 chars
			// create temp file and copy
			Path temp = Files.createTempFile(prefix, suffix);
			temp.toFile().deleteOnExit();
			Files.copy(in, temp, StandardCopyOption.REPLACE_EXISTING);
			return temp.toAbsolutePath().toString();
		}
	}

	public static void setWallpaper(String imageName) {
		if (!Game.isScrumOS) return;

		String wallpaperPath = null;
		try {
			wallpaperPath = copyResourceToTemp("wallpapers/" + imageName + ".jxl");
		} catch (IOException exception) {
			exception.printStackTrace();
			return;
		}

		List<String> args = new ArrayList<String>();
		args.add("hsetroot");
		args.add("-fill");
		args.add(wallpaperPath);

		ProcessBuilder builder = new ProcessBuilder(args);

		try {
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}