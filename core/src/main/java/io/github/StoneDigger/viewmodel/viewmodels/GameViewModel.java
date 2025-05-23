package io.github.StoneDigger.viewmodel.viewmodels;

import io.github.StoneDigger.model.models.*;

public class GameViewModel {
    private final GameModel gameModel;

    public GameViewModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    /// main logic
    public void handleInput(Direction direction) {
        gameModel.movePlayer(direction);
    }

    // rock falling mechanic
    public void update(float delta) {}

    public GameModel getGameModel() { return gameModel; }
}
