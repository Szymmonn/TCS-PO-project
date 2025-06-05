package io.github.StoneDigger.view.configs;

import io.github.StoneDigger.utils.config.ConfigLoader;
import io.github.StoneDigger.utils.config.Configuration;

import static io.github.StoneDigger.view.Assets.gameScreenPropertiesPath;

public class GameScreenPropertiesLoader {
    private static GameScreenProperties instance;
    private static final String path = gameScreenPropertiesPath;

    private GameScreenPropertiesLoader() {}

    public static GameScreenProperties getInstance() {
        if(instance == null) {

            Configuration config = new ConfigLoader(path);

            float blockSize = configToFloat("blockSize", config, 100f);
            int blocksInViewWidth = configToInt("blockInViewWidth", config, 21);
            int blocksInViewHeight = configToInt("blocksInViewHeight", config, 12);
            float hudSize = configToFloat("hudSize", config, 100f);
            String backgroundColor = configToString("backgroundColor", config, "#000000FF");

            instance = new GameScreenProperties(
                blockSize,
                blocksInViewWidth,
                blocksInViewHeight,
                hudSize,
                backgroundColor
            );
        }

        return instance;
    }

    private static int configToInt(String key, Configuration config, int defaultValue) {
        String value = config.getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    private static float configToFloat(String key, Configuration config, float defaultValue) {
        String value = config.getProperty(key);
        return value != null ? Float.parseFloat(value) : defaultValue;
    }

    private static String configToString(String key, Configuration config, String defaultValue) {
        String value = config.getProperty(key);
        return value != null ? value : defaultValue;
    }
}
