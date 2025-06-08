package io.github.StoneDigger.view.configs;

import io.github.StoneDigger.utils.config.ConfigLoader;
import io.github.StoneDigger.utils.config.Configuration;

import static io.github.StoneDigger.view.Assets.settingsViewPropertiesPath;

public class SettingsViewPropertiesLoader {
    private static SettingsViewProperties instance;
    private static final String path = settingsViewPropertiesPath;

    private SettingsViewPropertiesLoader() {}

    public static SettingsViewProperties getInstance() {
        if (instance == null) {
            Configuration config = new ConfigLoader(path);

            instance = new SettingsViewProperties(
                configToFloat(config, "settingsButtonOffsetX", -100f),
                configToFloat(config, "settingsButtonSize", 100f),

                configToFloat(config, "sliderWidth", 700f),
                configToFloat(config, "sliderHeight", 40f),
                configToFloat(config, "knobWidth", 24f),
                configToFloat(config, "knobHeight", 70f),
                configToFloat(config, "sliderMinValue", 0f),
                configToFloat(config, "sliderMaxValue", 4f),
                configToFloat(config, "sliderStepValue", 1f),
                configToString(config, "sliderColor", "#FFD700"),
                configToString(config, "knobColor", "#FFA500"),
                configToString(config, "knobBorderColor", "#FF0000"),
                configToString(config, "sliderStyleName", "custom-slider"),

                configToFloat(config, "musicSoundLabelFontSize", 40f),
                configToString(config, "musicSoundLabelFontColor", "#FFFFFFFF"),
                configToString(config, "musicSoundLabelFontBorderColor", "#000000FF"),
                configToString(config, "musicText", "music"),
                configToString(config, "soundText", "sound"),

                configToFloat(config, "settingsTitleFontSize", 60f),
                configToString(config, "settingsTitleFontColor", "#FFFFFFFF"),
                configToString(config, "settingsTitleFontBorderColor", "#000000FF"),
                configToString(config, "settingsTitleText", "SETTINGS"),

                configToString(config, "keyStyleBgColor", "#FF0000"),
                configToString(config, "keyStyleBorderColor", "#00FF00"),
                configToFloat(config, "keyStyleFontSize", 24f),
                configToString(config, "keyStyleFontBorderColor", "#000000FF"),

                configToFloat(config, "keybindsTitleFontSize", 48f),
                configToString(config, "keybindsTitleFontColor", "#FFFFFFFF"),
                configToString(config, "keybindsTitleFontBorderColor", "#000000FF"),
                configToString(config, "keybindsTitleText", "KEY BINDS"),

                configToString(config, "moveUpText", "Move Up Key"),
                configToString(config, "moveDownText", "Move Down Key"),
                configToString(config, "moveLeftText", "Move Left Key"),
                configToString(config, "moveRightText", "Move Right Key"),

                configToString(config, "keyUpText", "keyUp"),
                configToString(config, "keyDownText", "keyDown"),
                configToString(config, "keyLeftText", "keyLeft"),
                configToString(config, "keyRightText", "keyRight"),

                configToString(config, "backToMenuText", "Back to Menu"),
                configToString(config, "backToGameText", "Back to Game"),
                configToString(config, "bindsButtonText", "Key Binds"),
                configToString(config, "applyText", "Apply"),
                configToString(config, "backText", "Back"),

                configToFloat(config, "createTextButtonFontSize", 30f),
                configToString(config, "createTextButtonFontColor", "#0000FFFF"),
                configToString(config, "createTextButtonStyleUpMainColor", "#D3D3D3"),
                configToString(config, "createTextButtonStyleUpBorderColor", "#0000FF"),
                configToString(config, "createTextButtonStyleDownMainColor", "#A9A9A9"),
                configToString(config, "createTextButtonStyleDownBorderColor", "#0000FF"),

                configToFloat(config, "mainTableWidth", 1000f),
                configToFloat(config, "mainTableHeight", 900f),
                configToFloat(config, "mainTablePosX", 550f),
                configToFloat(config, "mainTablePosY", 150f),

                configToFloat(config, "labelWidth", 200f),
                configToFloat(config, "labelPadRight", 20f),
                configToFloat(config, "sliderHeightRow", 60f),
                configToFloat(config, "labelPadBottom", 20f),

                configToFloat(config, "bindsButtonPadTop", 100f),
                configToFloat(config, "bindsButtonWidth", 400f),
                configToFloat(config, "backToMenuButtonPadTop", 50f),
                configToFloat(config, "backToMenuButtonWidth", 450f),

                configToFloat(config, "keyButtonWidth", 300f),
                configToFloat(config, "keyButtonHeight", 100f),

                configToFloat(config, "keyGroupPad", 20f),
                configToFloat(config, "keybindsButtonRowPadTop", 40f),
                configToFloat(config, "keybindsButtonWidth", 300f),

                configToFloat(config, "roundedBoxX", 300f),
                configToFloat(config, "roundedBoxY", 150f),
                configToFloat(config, "roundedBoxWidth", 1500f),
                configToFloat(config, "roundedBoxHeight", 900f),
                configToFloat(config, "roundedBoxCornerRadius", 50f),
                configToFloat(config, "roundedBoxBorderWidth", 50f),

                configToFloat(config, "rowButtonPadRight", 23f)
            );
        }
        return instance;
    }

    private static float configToFloat(Configuration config, String key, float defaultVal) {
        String val = config.getProperty(key);
        return val != null ? Float.parseFloat(val) : defaultVal;
    }

    private static String configToString(Configuration config, String key, String defaultVal) {
        String val = config.getProperty(key);
        return val != null ? val : defaultVal;
    }
}

