package io.github.StoneDigger.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.StoneDigger.Game.GameStart;

public class DesktopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("StoneDigger");
    //    config.setWindowedMode((int)SCREEN_WIDTH, (int)SCREEN_HEIGHT);
        config.setWindowedMode(1600, 900);
        new Lwjgl3Application(new GameStart(), config);
    }
}
