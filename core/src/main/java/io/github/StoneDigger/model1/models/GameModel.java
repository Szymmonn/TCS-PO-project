package io.github.StoneDigger.model1.models;

import io.github.StoneDigger.model.Interfaces.EDirections;

public class GameModel {
    private PlayerModel playerModel;
    private BoardModel boardModel;

    public GameModel(PlayerModel playerModel, BoardModel boardModel) {
        this.playerModel = playerModel;
        this.boardModel = boardModel;
    }

    public BoardModel getBoard() {
        return boardModel;
    }

    public PlayerModel getPlayer() {
        return playerModel;
    }

    public void setBoardModel(BoardModel boardModel) {
        this.boardModel = boardModel;
    }

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    public void movePlayer(EDirections direction) {
        int newX = playerModel.getPositionX();
        int newY = playerModel.getPositionY();
        switch (direction) {
            case UP:
                newY++;
                break;
            case DOWN:
                newY--;
                break;
            case RIGHT:
                newX++;
                break;
            case LEFT:
                newX--;
                break;
        }
        TileType tileToMove = boardModel.getTile(newX, newY);
        if (tileToMove == TileType.EMPTY || tileToMove == TileType.START) {
            playerModel.moveTo(newX, newY);
            return;
        }
        if (tileToMove == TileType.WALL) return;
        if (tileToMove == TileType.DIRT) {
            boardModel.updateTile(newX, newY, TileType.EMPTY);
            playerModel.moveTo(newX, newY);
            return;
        }
         /*
         / TODO: implement rock, diamond, exit mechanics
        */
        if (tileToMove == TileType.ROCK) return;

        if (tileToMove == TileType.DIAMOND) {
            boardModel.updateTile(newX, newY, TileType.EMPTY);
            playerModel.moveTo(newX, newY);
        }
        if (tileToMove == TileType.EXIT) System.exit(0);
    }
}
