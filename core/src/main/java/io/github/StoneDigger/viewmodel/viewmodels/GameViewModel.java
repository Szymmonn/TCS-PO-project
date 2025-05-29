package io.github.StoneDigger.viewmodel.viewmodels;


import io.github.StoneDigger.model.Classes.Board;
import io.github.StoneDigger.model.Classes.GameLogic;
import io.github.StoneDigger.model.Classes.Player;
import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model1.models.BoardModel;
import io.github.StoneDigger.model1.models.PlayerModel;

public class GameViewModel {
    private GameLogic gameLogic;
    private Board board;
    private Player player;

    public GameViewModel(Player playerModel, Board boardModel) {
        gameLogic = new GameLogic();
        this.player = playerModel;
        this.board = boardModel;
    }

    public Board getBoardModel() {
        return board;
    }

    public Player getPlayerModel() {
        return player;
    }

    public void setBoardModel(Board boardModel) {
        this.board = boardModel;
    }

    public void setPlayerModel(Player playerModel) {
        this.player = playerModel;
    }
    public int getPlayerPositionX() {
        return player.getPosition().x;
    }

    public int getPlayerPositionY() {
        return player.getPosition().y;
    }

    public void handleInput(EDirections dir) {

    }
}
