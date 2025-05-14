package io.github.StoneDigger.viewmodels;

import io.github.StoneDigger.BoardGenerators.RandomBoardGenerator;
import io.github.StoneDigger.models.BoardModel;
import io.github.StoneDigger.models.PlayerModel;
import io.github.StoneDigger.models.TileType;

public class GameViewModel {
    private PlayerModel playerModel;
    private BoardModel boardModel;

    private Runnable levelLoadedCallback;

    public GameViewModel() {}

    public void setLevelLoadedCallback(Runnable callback) {
        this.levelLoadedCallback = callback;
    }

    public void prepareLevel(int width, int height) {
        playerModel = new PlayerModel(1, 1);
        boardModel  = new RandomBoardGenerator(0.1f,0.1f,0.1f)
            .generate(width, height, 1, 1);

        if (levelLoadedCallback != null) {
            levelLoadedCallback.run();
        }
    }

    /// main logic

    public void handleInput(String direction) {
        int newX = playerModel.getPositionX();
        int newY = playerModel.getPositionY();
        switch (direction) {
            case "UP": newY++; break;
            case "DOWN": newY--; break;
            case "RIGHT": newX++; break;
            case "LEFT": newX--; break;
        }
        TileType tileToMove = boardModel.getTile(newX, newY);
        if(tileToMove == TileType.EMPTY || tileToMove == TileType.START) {
            playerModel.moveTo(newX, newY);
            return;
        }
        if(tileToMove == TileType.WALL) return;
        if(tileToMove == TileType.DIRT) {
            boardModel.updateTile(newX, newY, TileType.EMPTY);
            playerModel.moveTo(newX, newY);
            return;
        }
         /*
         / TODO: implement rock, diamond, exit mechanics
        */
        if(tileToMove == TileType.ROCK) return;

        if(tileToMove == TileType.DIAMOND) {
            boardModel.updateTile(newX, newY, TileType.EMPTY);
            playerModel.moveTo(newX, newY);
        }
        if(tileToMove == TileType.EXIT) {
          prepareLevel(20, 30);
        }
    }

    // rock falling mechanic
    public void update(float delta) {}

    public int getPlayerPositionX() { return playerModel.getPositionX(); }
    public int getPlayerPositionY() { return playerModel.getPositionY(); }
    public BoardModel getBoardModel() { return boardModel; }
    public PlayerModel getPlayerModel() { return playerModel; }
}
