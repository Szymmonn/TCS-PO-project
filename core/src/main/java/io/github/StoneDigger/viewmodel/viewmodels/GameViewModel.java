package io.github.StoneDigger.viewmodel.viewmodels;


import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameLogic.GameLogic;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.EDirections;

public class GameViewModel {
    GameLogic gameLogic;

    public GameViewModel() {
        GameLogic gameLogic = new GameLogic();
    }
    public Board getBoard() {
        return gameLogic.getBoard();
    }
    public Player getPlayer() {
        return gameLogic.getPlayer();
    }
//    public List<Opponent> getOpponentList() {
//        return opponentList;
//    }
//
//    public int getPlayerPositionX() {
//        return player.getPosition().x;
//    }
//    public int getPlayerPositionY() {
//        return player.getPosition().y;
//    }

    public void handleInput(EDirections direction) {
        gameLogic.getPlayer().move(direction);
    }
}
