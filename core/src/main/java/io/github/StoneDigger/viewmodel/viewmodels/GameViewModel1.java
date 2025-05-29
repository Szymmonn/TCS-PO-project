package io.github.StoneDigger.viewmodel.viewmodels;


import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model1.models.BoardModel;
import io.github.StoneDigger.model1.models.GameModel;
import io.github.StoneDigger.model1.models.PlayerModel;

public class GameViewModel1 {
    private final GameModel gameModel;

    private Runnable levelLoadedCallback;

    public GameViewModel1(PlayerModel playerModel, BoardModel boardModel) {
        gameModel = new GameModel(playerModel, boardModel);
    }

    public void handleInput(EDirections direction) {
        gameModel.movePlayer(direction);
    }


    public int getPlayerPositionX() { return gameModel.getPlayer().getPositionX(); }
    public int getPlayerPositionY() { return gameModel.getPlayer().getPositionY(); }
}
