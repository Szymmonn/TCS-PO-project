package io.github.StoneDigger.viewmodel.viewmodels;


import io.github.StoneDigger.model.Classes.Board;
import io.github.StoneDigger.model.Classes.GameLogic;
import io.github.StoneDigger.model.Classes.Opponent;
import io.github.StoneDigger.model.Classes.Player;
import io.github.StoneDigger.model.Interfaces.EDirections;

import java.util.List;

public class GameViewModel {
    private Board board;
    private Player player;
    private List<Opponent> opponentList;

    public GameViewModel(Player player, Board board, List<Opponent> opponentList) {
        GameLogic gameLogic = new GameLogic();
        this.player = player;
        this.board = board;
        this.opponentList = opponentList;
    }

    public Board getBoard() {
        return board;
    }
    public Player getPlayer() {
        return player;
    }
    public List<Opponent> getOpponentList() {
        return opponentList;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setOpponentList(List<Opponent> opponentList) {
        this.opponentList = opponentList;
    }

    public int getPlayerPositionX() {
        return player.getPosition().x;
    }
    public int getPlayerPositionY() {
        return player.getPosition().y;
    }

    public void handleInput(EDirections direction) {
        player.move(direction);
    }
}
