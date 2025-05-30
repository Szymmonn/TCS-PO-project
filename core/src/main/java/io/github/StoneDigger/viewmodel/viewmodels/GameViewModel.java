package io.github.StoneDigger.viewmodel.viewmodels;


import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.ELevelType;
import io.github.StoneDigger.model.GameLogic.GameLogic;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.Level.Managers.PlayerManager;

public class GameViewModel {

    public GameViewModel(ELevelType standard) {
        startTheGame(standard);
    }

    public Board getBoard() {
        return LevelManager.getBoard();
    }
    public Player getPlayer() {
        return PlayerManager.getPlayer();
    }
    public ILevelStats getLevelStats() { return LevelManager.getStats(); }

//    public List<Opponent> getOpponentList() {
//        return opponentList;
//    }

    public void startTheGame(ELevelType levelType) {
        GameLogic.startTheGame(levelType);
    }

    public void update(float delta) {
        GameLogic.tick(delta);
        //if (GameLogic.isGameOver()) {}
    }

    public void handleInput(EDirections direction) {
        PlayerManager.getPlayer().move(direction);
    }
}
