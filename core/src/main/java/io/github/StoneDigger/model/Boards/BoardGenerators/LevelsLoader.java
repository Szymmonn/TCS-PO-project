package io.github.StoneDigger.model.Boards.BoardGenerators;

import io.github.StoneDigger.utils.config.ConfigLoader;
import io.github.StoneDigger.utils.config.Configuration;

public class LevelsLoader {
    private static final String path = "config/Levels.properties";
    private static final Configuration config = new ConfigLoader(path);

    public static int levelsToWinRandom() {
        String value = config.getProperty("levelsToWinRandom");
        return value == null ? 5 : Integer.parseInt(value);
    }

    public static int levelToWinStandard() {
        String value = config.getProperty("levelsToWinStandard");
        return value == null ? 3 : Integer.parseInt(value);
    }

    // MOVED to standardBoardGenerator
    /*public static char[][] getStandardLevel(int levelNumber) {
        ...
    }*/
}
