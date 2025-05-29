package io.github.StoneDigger.viewmodel.viewmodels;


import io.github.StoneDigger.model.GameLogic.GameLogic;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.EDirections;

public class GameViewModel {
    GameLogic gameLogic;
    Player player;

    public GameViewModel(Player player) {
        this.player = player;
        GameLogic gameLogic = new GameLogic();
    }
//    public Board getBoard() {
//        return board;
//    }
//    public Player getPlayer() {
//        return player;
//    }
//    public List<Opponent> getOpponentList() {
//        return opponentList;
//    }
//    public void setBoard(Board board) {
//        this.board = board;
//    }
//    public void setPlayer(Player player) {
//        this.player = player;
//    }
//    public void setOpponentList(List<Opponent> opponentList) {
//        this.opponentList = opponentList;
//    }
//
//    public int getPlayerPositionX() {
//        return player.getPosition().x;
//    }
//    public int getPlayerPositionY() {
//        return player.getPosition().y;
//    }

    public void handleInput(EDirections direction) {
        player.move(direction);
    }
}
