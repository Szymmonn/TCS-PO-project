package io.github.StoneDigger.viewmodel.viewmodels;


import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.ELevelType;
import io.github.StoneDigger.model.Boards.IBoard;
import io.github.StoneDigger.model.GameLogic.GameLogic;
import io.github.StoneDigger.model.GameObjects.Entities.IOpponent;
import io.github.StoneDigger.model.GameObjects.Entities.IPlayer;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.Level.Managers.PlayerManager;

public class GameViewModel {
    private final GameLogic gameLogic;
    private boolean newGame = false;
    private boolean isGameWon = false;
    ELevelType levelType;

    public GameViewModel(ELevelType levelType) {
        gameLogic = new GameLogic();
        startTheGame(levelType);
    }

    public IBoard getBoard() {
        return gameLogic.getBoard();
    }
    public IPlayer getPlayer() {
        return gameLogic.getPlayer();
    }
    public IOpponent getOpponent() {
        return gameLogic.getOpponent();
    }

    public ILevelStats getLevelStats() { return gameLogic.getLevelStats(); }

//    public List<Opponent> getOpponentList() {
//        return opponentList;
//    }

    public void startTheGame(ELevelType levelType) {
        isGameWon = false;
        gameLogic.startTheGame(levelType);
    }

    public void update(float delta) {
        gameLogic.tick(delta);

        if (gameLogic.getIsNewGame()) {
            newGame = true;
        }

        if(gameLogic.getIsNewGame()) {
            isGameWon = true;
        }
    }

    public void handleInput(EDirections direction) {
        gameLogic.movePlayer(direction);
    }

    public boolean getIsNewGame() {
        boolean newGameCopy = newGame;
        newGame = false;
        return newGameCopy;
    }

}
