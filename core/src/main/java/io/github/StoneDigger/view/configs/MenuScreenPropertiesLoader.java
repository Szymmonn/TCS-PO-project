package io.github.StoneDigger.view.configs;

import io.github.StoneDigger.utils.config.ConfigLoader;
import io.github.StoneDigger.utils.config.Configuration;

import static io.github.StoneDigger.view.Assets.menuScreenPropertiesPath;

public class MenuScreenPropertiesLoader {
    private static MenuScreenProperties instance;
    private static final String path = menuScreenPropertiesPath;

    private MenuScreenPropertiesLoader() {}

    public static MenuScreenProperties getInstance() {
        if(instance == null) {

            Configuration config = new ConfigLoader(path);

            final float worldWidth = configToFloat("worldWidth", config, 800);
            final float worldHeight = configToFloat("worldHeight", config, 480);

            final float fontSize = configToFloat("fontSize", config, 60);
            final String fontColor = configToString("fontColor", config, "#FFFFFFFF"); // white
            final String fontBorderColor = configToString("fontBorderColor", config, "#000000FF"); // black
            final float fontBorderWidth = configToFloat("fontBorderWidth", config, 2f);
            final String fontShadowColor = configToString("fontShadowColor", config, "#00000066"); // #00000066
            final float fontShadowOffsetX = configToFloat("fontShadowOffsetX", config, 2);
            final float fontShadowOffsetY = configToFloat("fontShadowOffsetY", config, 2);
            final String fontCharacterOptimization = configToString("fontCharacterOptimization", config, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789:!?.,'\"()-=+/\\ ");

            final String buttonStyleBackgroundColor = configToString("buttonStyleBackgroundColor", config, "#76D8FFFF"); // #76D8FFFF
            final String buttonStyleFontColor1 = configToString("buttonStyleFontColor1", config, "#B22222FF"); // #B22222FF
            final String buttonStyleFontColor2 = configToString("buttonStyleFontColor2", config, "#808080FF"); // #808080FF

            final float buttonWidth = configToFloat("buttonWidth", config, 300);
            final float buttonHeight = configToFloat("buttonHeight", config, 80);

            final float tableTitleStandardBottomPad = configToFloat("tableTitleStandardBottomPad", config, 30);
            final float tableStandardRandomBottomPad = configToFloat("tableStandardRandomBottomPad", config, 10);
            final float tableRandomEnterBottomPad = configToFloat("tableRandomEnterBottomPad", config, 30);

            final String backgroundColor = configToString("backgroundColor", config, "#FF6666");

            instance = new MenuScreenProperties(
                worldWidth,
                worldHeight,
                fontSize,
                fontColor,
                fontBorderColor,
                fontBorderWidth,
                fontShadowColor,
                fontShadowOffsetX,
                fontShadowOffsetY,
                fontCharacterOptimization,
                buttonStyleBackgroundColor,
                buttonStyleFontColor1,
                buttonStyleFontColor2,
                buttonWidth,
                buttonHeight,
                tableTitleStandardBottomPad,
                tableStandardRandomBottomPad,
                tableRandomEnterBottomPad,
                backgroundColor
            );
        }

        return instance;
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
