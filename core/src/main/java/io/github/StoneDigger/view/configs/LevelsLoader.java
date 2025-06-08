package io.github.StoneDigger.view.configs;

import io.github.StoneDigger.utils.config.ConfigLoader;
import io.github.StoneDigger.utils.config.Configuration;

public class LevelsLoader {
    private static final String path = "config/Levels.properties";

    public static char[][] getStandardLevel(int levelNumber) {
        Configuration config = new ConfigLoader(path);

        int w = Integer.parseInt(config.getProperty("level" + levelNumber + "width"));
        int h = Integer.parseInt(config.getProperty("level" + levelNumber + "height"));
        String arr = config.getProperty("level" + levelNumber);

        char[][] level = new char[w][h];

        int start = 0;
        while(arr.charAt(start) != ';') start ++;
        start++;
        for(int y = h-1; y >= 0; y --) {
            for(int x = 0; x < w; x ++) {
                level[x][y] = arr.charAt(start);
                start ++;
            }
        }
        return level;
    }
}
