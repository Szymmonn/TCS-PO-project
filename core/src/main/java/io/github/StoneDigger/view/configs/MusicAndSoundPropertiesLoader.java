package io.github.StoneDigger.view.configs;

import io.github.StoneDigger.utils.config.ConfigLoader;
import io.github.StoneDigger.utils.config.Configuration;

public class MusicAndSoundPropertiesLoader {
    public static MusicAndSoundProperties instance;
    public static final String path = "conifg/MusicAndSoundProperties.properties";

    public static MusicAndSoundProperties getInstance() {
        if(instance == null) {
            Configuration config = new ConfigLoader(path);

            float musicVolumeMultiplier = configToFloat("musicVolumeMultiplier", config, 0.4f);
            float soundVolume = configToFloat("soundVolume", config, 1f);

            instance = new MusicAndSoundProperties(
                musicVolumeMultiplier,
                soundVolume
            );
        }

        return instance;
    }

    private static float configToFloat(String key, Configuration config, float defaultValue) {
        String value = config.getProperty(key);
        return value != null ? Float.parseFloat(value) : defaultValue;
    }
}
