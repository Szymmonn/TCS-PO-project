package io.github.StoneDigger.utils.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.util.Properties;

public class ConfigChanger implements ConfigurationChanger{
    private final Properties properties;

    public ConfigChanger(String path) {
        properties = new Properties();
        FileHandle file = Gdx.files.internal(path);
        try {
            properties.load(file.read());
        } catch (IOException e){
            throw new RuntimeException("Config file not found!");
        }
    }


    @Override
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
