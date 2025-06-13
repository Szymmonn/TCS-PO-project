package io.github.StoneDigger.view.configs;

import io.github.StoneDigger.utils.config.ConfigLoader;
import io.github.StoneDigger.utils.config.Configuration;

public class WinnerScreenPropertiesLoader {
    private static WinnerScreenProperties instance;
    private static final String path = "config/WinnerScreenProperties.properties";

    public static WinnerScreenProperties getInstance() {
        if (instance == null) {
            Configuration config = new ConfigLoader(path);

            float worldWidth            = configToFloat("worldWidth", config, 800f);
            float worldHeight           = configToFloat("worldHeight", config, 480f);

            int wonFontSize          = configToInt("wonFontSize", config, 70);
            String wonFontBorderColor  = configToString("wonFontBorderColor", config, "#000000FF");
            String wonFontColor        = configToString("wonFontColor", config, "#FF0000FF");
            float wonLabelWidth        = configToFloat("wonLabelWidth", config, 700f);
            float wonLabelHeight       = configToFloat("wonLabelHeight", config, 100f);
            float wonLabelPositionX    = configToFloat("wonLabelPositionX", config, 70f);
            float wonLabelPositionY    = configToFloat("wonLabelPositionY", config, 350f);

            int backFontSize          = configToInt("backFontSize", config, 30);
            String backFontBorderColor  = configToString("backFontBorderColor", config, "#000000FF");
            String backFontColor        = configToString("backFontColor", config, "#0000FFFF");
            float backLabelWidth        = configToFloat("backLabelWidth", config, 700f);
            float backLabelHeight       = configToFloat("backLabelHeight", config, 100f);
            float backLabelPositionX    = configToFloat("backLabelPositionX", config, 50f);
            float backLabelPositionY    = configToFloat("backLabelPositionY", config, 50f);

            instance = new WinnerScreenProperties(
                worldWidth,
                worldHeight,
                wonFontSize,
                wonFontBorderColor,
                wonFontColor,
                wonLabelWidth,
                wonLabelHeight,
                wonLabelPositionX,
                wonLabelPositionY,
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
