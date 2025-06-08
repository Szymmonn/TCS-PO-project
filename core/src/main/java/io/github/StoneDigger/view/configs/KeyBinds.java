package io.github.StoneDigger.view.configs;

import com.badlogic.gdx.Input;
import io.github.StoneDigger.utils.config.ConfigChanger;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class KeyBinds {
    private final ConfigChanger configChanger;

    public KeyBinds() {
        configChanger = new ConfigChanger("config/KeyBinds.properties");
        if(getUpKey() == Input.Keys.UNKNOWN) {
            setUpKey(Input.Keys.UP);
        }
        if(getDownKey() == Input.Keys.UNKNOWN) {
            setDownKey(Input.Keys.DOWN);
        }
        if(getLeftKey() == Input.Keys.UNKNOWN) {
            setLeftKey(Input.Keys.LEFT);
        }
        if(getRightKey() == Input.Keys.UNKNOWN) {
            setRightKey(Input.Keys.RIGHT);
        }
    }

    public int getUpKey() {
        return Integer.parseInt(configChanger.getProperty("moveUpKey"));
    }
    public int getDownKey() {
        return Integer.parseInt(configChanger.getProperty("moveDownKey"));
    }
    public int getRightKey() {
        return Integer.parseInt(configChanger.getProperty("moveRightKey"));
    }
    public int getLeftKey() {
        return Integer.parseInt(configChanger.getProperty("moveLeftKey"));
    }

    public void setUpKey(int keycode) {
        configChanger.setProperty("moveUpKey", String.valueOf(keycode));
    }
    public void setDownKey(int keycode) {
        configChanger.setProperty("moveDownKey", String.valueOf(keycode));
    }
    public void setLeftKey(int keycode) {
        configChanger.setProperty("moveLeftKey", String.valueOf(keycode));
    }
    public void setRightKey(int keycode) {
        configChanger.setProperty("moveRightKey", String.valueOf(keycode));
    }
}
