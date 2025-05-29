package io.github.StoneDigger.viewmodel.viewmodels;


import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameLogic.GameLogic;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;

import java.util.logging.Level;

public class GameViewModel {

    public GameViewModel() {
        startTheGame();
    }

    public Board getBoard() {
        return GameLogic.getBoard();
    }
    public Player getPlayer() {
        return LevelManager.getPlayer();
    }

//    public List<Opponent> getOpponentList() {
//        return opponentList;
//    }

    public void startTheGame() {
        GameLogic.init();
    }

    public void update(float delta) { GameLogic.tick(delta); }

    public void handleInput(EDirections direction) {
        LevelManager.getPlayer().move(direction);
    }
}
