package io.github.StoneDigger;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MyGame extends Game {
    FitViewport viewport;

    public static int WIDTH = 800;
    public static int HEIGHT = 480;
    private GameScreen gameScreen;

    @Override
    public void create() {
        Assets.load();
        viewport = new FitViewport(8f, 5f);
        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); // true centers the camera
    }

    @Override
    public void dispose() {
        Assets.dispose();
        gameScreen.dispose();
    }

}
