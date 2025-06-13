package io.github.StoneDigger.model.Boards.BoardGenerators;

import io.github.StoneDigger.model.Interfaces.IBoardGenerator;
import io.github.StoneDigger.utils.config.ConfigLoader;
import io.github.StoneDigger.utils.config.Configuration;

public class StandardBoardGenerator implements IBoardGenerator {
    private static final String path = "config/Levels.properties";
    private static final Configuration config = new ConfigLoader(path);

    public static char[][] generate(int levelNumber) {
        int w = Integer.parseInt(config.getProperty("level" + levelNumber + "width"));
        int h = Integer.parseInt(config.getProperty("level" + levelNumber + "height"));
        String arr = config.getProperty("level" + levelNumber);

        char[][] level = new char[h][w];

        int start = 0;
        while(arr.charAt(start) != ';') start ++;
        start++;
        for(int y = 0; y < h; y ++) {
            for(int x = 0; x < w; x ++) {
                if(start == w*h) System.out.println("XD");
                level[y][x] = arr.charAt(start);
                start ++;
                System.out.print(level[y][x]);
            }
            System.out.println();
        }
        return level;
    }
}
