package io.github.StoneDigger.viewmodel.viewmodels;


import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameLogic.GameLogic;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;
import io.github.StoneDigger.model.Level.PlayerManager;

public class GameViewModel {

    public GameViewModel() {
        startTheGame();
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

    public void startTheGame() {
        GameLogic.startTheGame();
    }

    public void update(float delta) {
        GameLogic.tick(delta);
        //if (GameLogic.isGameOver()) {}
    }

    public void handleInput(EDirections direction) {
        PlayerManager.getPlayer().move(direction);
    }
}
