package io.github.StoneDigger.view.Game;

import com.badlogic.gdx.Game;
import io.github.StoneDigger.view.Assets;
import io.github.StoneDigger.view.screen.LoserScreen;
import io.github.StoneDigger.view.screen.MenuScreen;
import io.github.StoneDigger.view.screen.WinnerScreen;

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
