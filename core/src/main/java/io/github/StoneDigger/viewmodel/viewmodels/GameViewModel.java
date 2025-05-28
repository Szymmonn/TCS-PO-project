package io.github.StoneDigger.viewmodel.viewmodels;


import io.github.StoneDigger.model1.BoardGenerators.RandomBoardGenerator;
import io.github.StoneDigger.model1.models.BoardModel;
import io.github.StoneDigger.model1.models.Direction;
import io.github.StoneDigger.model1.models.GameModel;
import io.github.StoneDigger.model1.models.PlayerModel;
import io.github.StoneDigger.model1.models.RockModel;

public class GameViewModel {
    private GameModel gameModel;

    private Runnable levelLoadedCallback;

    public GameViewModel(PlayerModel playerModel, BoardModel boardModel, RockModel rockModel) {
        gameModel = new GameModel(playerModel, boardModel, rockModel);
    }

    public void setLevelLoadedCallback(Runnable callback) {
        this.levelLoadedCallback = callback;
    }

    public void prepareLevel(int width, int height) {
        gameModel.setPlayerModel(new PlayerModel(1, 1));
        gameModel.setBoardModel(new RandomBoardGenerator(0.1f,0.1f,0.1f)
            .generate(width, height, 1, 1));

        if (levelLoadedCallback != null) {
            levelLoadedCallback.run();
        }
    }

    public void handleInput(Direction direction) {
        gameModel.movePlayer(direction);
    }


    // rock falling mechanic
    public void update(float delta) {}

    public int getPlayerPositionX() { return gameModel.getPlayer().getPositionX(); }
    public int getPlayerPositionY() { return gameModel.getPlayer().getPositionY(); }
    public BoardModel getBoardModel() { return gameModel.getBoard(); }
    public PlayerModel getPlayerModel() { return gameModel.getPlayer(); }
}
