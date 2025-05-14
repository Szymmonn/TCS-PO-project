package io.github.StoneDigger.Game;

import com.badlogic.gdx.Game;
import io.github.StoneDigger.Assets;
import io.github.StoneDigger.screen.GameScreen;

public class GameStart extends Game {
    private GameScreen gameScreen;

    @Override
    public void create() {
        Assets.load();
        gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }
}
