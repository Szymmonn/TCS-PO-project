package io.github.StoneDigger.view.screen;

import com.badlogic.gdx.ScreenAdapter;
import io.github.StoneDigger.view.Game.GameStart;

public class SettingsScreen extends ScreenAdapter {
    private final GameStart gameStart;

    public SettingsScreen(GameStart gameStart) {
        this.gameStart = gameStart;
    }
}
