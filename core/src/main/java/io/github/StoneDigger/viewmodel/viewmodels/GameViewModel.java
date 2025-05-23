package io.github.StoneDigger.viewmodel.viewmodels;


import io.github.StoneDigger.model.BoardGenerators.BoardValidators.SimpleBoardValidator;
import io.github.StoneDigger.model.BoardGenerators.RandomBoardGenerator;
import io.github.StoneDigger.model.models.BoardModel;
import io.github.StoneDigger.model.models.Direction;
import io.github.StoneDigger.model.models.GameModel;
import io.github.StoneDigger.model.models.PlayerModel;

public class GameViewModel {
    private GameModel gameModel;

    private Runnable levelLoadedCallback;

    public GameViewModel(PlayerModel playerModel, BoardModel boardModel) {
        gameModel = new GameModel(playerModel, boardModel);
    }

    public void setLevelLoadedCallback(Runnable callback) {
        this.levelLoadedCallback = callback;
    }

    public void prepareLevel(int width, int height) {
        gameModel.setPlayerModel(new PlayerModel(1, 1));
        SimpleBoardValidator validator = new SimpleBoardValidator();
        BoardModel boardModel = new RandomBoardGenerator(0.1f,0.1f,0.1f)
            .generate(width, height, 1, 1);

        while(!validator.validate(boardModel)) {
            gameModel.setBoardModel(new RandomBoardGenerator(0.1f,0.1f,0.1f)
                .generate(width, height, 1, 1));
        }

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
