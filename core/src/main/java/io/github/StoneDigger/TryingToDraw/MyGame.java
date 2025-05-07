package io.github.StoneDigger.TryingToDraw;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import io.github.StoneDigger.Assets;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MyGame extends Game {
    ExtendViewport viewport;

    public static int WIDTH = 800;
    public static int HEIGHT = 480;
    private MyGameScreen gameScreen;

    @Override
    public void create() {
        Assets.load();
        viewport = new ExtendViewport(WIDTH, HEIGHT);
        gameScreen = new MyGameScreen(this);
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
