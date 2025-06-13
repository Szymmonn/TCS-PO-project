package io.github.StoneDigger.view.configs;

import io.github.StoneDigger.utils.config.ConfigLoader;
import io.github.StoneDigger.utils.config.Configuration;

public class LoserScreenPropertiesLoader {
    private static LoserScreenProperties instance;
    private static final String path = "config/LoserScreenProperties.properties";

    public static LoserScreenProperties getInstance() {
        if (instance == null) {
            Configuration config = new ConfigLoader(path);

            float worldWidth            = configToFloat("worldWidth", config, 800f);
            float worldHeight           = configToFloat("worldHeight", config, 480f);

            int lostFontSize          = configToInt("lostFontSize", config, 70);
            String lostFontBorderColor  = configToString("lostFontBorderColor", config, "#000000FF");
            String lostFontColor        = configToString("lostFontColor", config, "#FF0000FF");
            float lostLabelWidth        = configToFloat("lostLabelWidth", config, 700f);
            float lostLabelHeight       = configToFloat("lostLabelHeight", config, 100f);
            float lostLabelPositionX    = configToFloat("lostLabelPositionX", config, 64f);
            float lostLabelPositionY    = configToFloat("lostLabelPositionY", config, 350f);

            int backFontSize          = configToInt("backFontSize", config, 30);
            String backFontBorderColor  = configToString("backFontBorderColor", config, "#000000FF");
            String backFontColor        = configToString("backFontColor", config, "#0000FFFF");
            float backLabelWidth        = configToFloat("backLabelWidth", config, 700f);
            float backLabelHeight       = configToFloat("backLabelHeight", config, 100f);
            float backLabelPositionX    = configToFloat("backLabelPositionX", config, 50f);
            float backLabelPositionY    = configToFloat("backLabelPositionY", config, 50f);

            instance = new LoserScreenProperties(
                worldWidth,
                worldHeight,
                lostFontSize,
                lostFontBorderColor,
                lostFontColor,
                lostLabelWidth,
                lostLabelHeight,
                lostLabelPositionX,
                lostLabelPositionY,
                backFontSize,
                backFontBorderColor,
                backFontColor,
                backLabelWidth,
                backLabelHeight,
                backLabelPositionX,
                backLabelPositionY
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
