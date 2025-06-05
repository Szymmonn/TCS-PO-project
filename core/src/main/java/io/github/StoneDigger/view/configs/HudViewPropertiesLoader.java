package io.github.StoneDigger.view.configs;

import io.github.StoneDigger.utils.config.ConfigLoader;
import io.github.StoneDigger.utils.config.Configuration;

import static io.github.StoneDigger.view.Assets.hudViewPropertiesPath;

public class HudViewPropertiesLoader {
    private static HudViewProperties instance;
    private final static String path = hudViewPropertiesPath;

    public static HudViewProperties getInstance() {
        if(instance == null) {
            Configuration config = new ConfigLoader(path);

            String hudBgColor = configToString("hudBgColor", config, "#B22222FF");
            int fontSize = configToInt("fontSize", config, 30);
            String fontBorderColor = configToString("fontBorderColor", config, "#000000FF");
            String fontColor = configToString("fontColor", config, "#FFFFFFFF");
            float fontScaleX = configToFloat("fontScaleX", config, 100);
            float fontScaleY = configToFloat("fontScaleY", config, 80);
            float settingsButtonPositionXOffset = configToFloat("settingsButtonPositionXOffset", config, -100);
            float settingsButtonWidth = configToFloat("settingsButtonWidth", config, 100);
            float settingsButtonHeight = configToFloat("settingsButtonHeight", config, 100);
            float diamondTablePad = configToFloat("diamondTablePad", config, 10);
            float diamondImageSize = configToFloat("diamondImageSize", config, 100);
            float diamondCollectedLabelSize = configToFloat("diamondCollectedLabel", config, 80);
            float hpTableWidthOffset = configToFloat("hpTableWidthOffset", config, -150);
            float hpImageSize = configToFloat("hpImageSize", config, 100);
            float hpImagePad = configToFloat("hpImagePad", config, 5);
            float levelLabelPositionXMultiplier = configToFloat("levelLabelPositionXMultiplier", config, 0.325f);
            float levelLabelPositionYPad = configToFloat("levelLabelPositionYPad", config, 4);
            float leveLabelWidth = configToFloat("levelLabelWidth", config, 180);
            float levelLabelHeight = configToFloat("levelLabelHeight", config, 80);
            float timeLabelPositionXMultiplier = configToFloat("timeLabelPositionXMultiplier", config, 0.5f);
            float timeLabelPad = configToFloat("timeLabelPad", config, 4);
            float timeLabelWidth = configToFloat("timeLabelWidth", config, 370);
            float timeLabelHeight = configToFloat("timeLabelHeight", config, 80);
            float labelTopPad = configToFloat("labelTopPad", config, 26);

            instance = new HudViewProperties(
                hudBgColor,
                fontSize,
                fontBorderColor,
                fontColor,
                fontScaleX,
                fontScaleY,
                settingsButtonPositionXOffset,
                settingsButtonWidth,
                settingsButtonHeight,
                diamondTablePad,
                diamondImageSize,
                diamondCollectedLabelSize,
                hpTableWidthOffset,
                hpImageSize,
                hpImagePad,
                levelLabelPositionXMultiplier,
                levelLabelPositionYPad,
                leveLabelWidth,
                levelLabelHeight,
                timeLabelPositionXMultiplier,
                timeLabelPad,
                timeLabelWidth,
                timeLabelHeight,
                labelTopPad
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
