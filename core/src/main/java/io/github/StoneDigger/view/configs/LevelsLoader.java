package io.github.StoneDigger.view.configs;

import io.github.StoneDigger.utils.config.ConfigLoader;
import io.github.StoneDigger.utils.config.Configuration;

public class LevelsLoader {
    private static final String path = "config/Levels.properties";

    public static char[][] getStandardLevel(int levelNumber) {
        Configuration config = new ConfigLoader(path);

        // supposing this is fine
        String raw = config.getProperty("level" + levelNumber);
        String[] rowStrings = raw.split(";");
        char[][] level = new char[rowStrings.length][];

        for (int i = 0; i < rowStrings.length; i++) {
            String[] cells = rowStrings[i].split(",");
            level[i] = new char[cells.length];
            for (int j = 0; j < cells.length; j++) {
                level[i][j] = cells[j].trim().isEmpty() ? ' ' : cells[j].trim().charAt(0);
            }
        }
        return level;
    }
}
