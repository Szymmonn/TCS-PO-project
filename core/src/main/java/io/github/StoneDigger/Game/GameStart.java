package io.github.StoneDigger.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import io.github.StoneDigger.Assets;
import io.github.StoneDigger.screen.GameScreen;
import io.github.StoneDigger.screen.MenuScreen;

public class GameStart extends Game {

    @Override
    public void create() {
        Assets.load();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }
}
