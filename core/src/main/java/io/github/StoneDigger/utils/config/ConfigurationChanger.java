package io.github.StoneDigger.utils.config;

public interface ConfigurationChanger extends Configuration {
    public void setProperty(String property, String value);
}
