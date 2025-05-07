package io.github.StoneDigger.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.StoneDigger.TryingToDraw.MyGame;

public class DesktopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("StoneDigger");
        config.setWindowedMode(800, 600);
        new Lwjgl3Application(new MyGame(), config);
    }
}
