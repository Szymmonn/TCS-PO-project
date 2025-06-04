package io.github.StoneDigger.utils.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.*;
import java.util.Properties;

public class ConfigLoader implements Configuration {
    private final Properties properties;

    public ConfigLoader(String fileName) {
        properties = new Properties();
        FileHandle file = Gdx.files.internal(fileName);
        try {
            properties.load(file.read());
        } catch (IOException e) {
            throw new RuntimeException("Config file not found!");
        }
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
