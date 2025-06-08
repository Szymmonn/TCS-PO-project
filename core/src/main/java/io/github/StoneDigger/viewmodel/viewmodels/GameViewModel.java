package io.github.StoneDigger.viewmodel.viewmodels;


import io.github.StoneDigger.model.GameLogic.ELevelType;
import io.github.StoneDigger.model.Boards.IBoard;
import io.github.StoneDigger.model.GameLogic.GameLogic;
import io.github.StoneDigger.model.GameObjects.Entities.IOpponent;
import io.github.StoneDigger.model.Interfaces.IPlayer;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Level.ILevelStats;

public class GameViewModel {
    private final GameLogic gameLogic;
    private boolean newGame = false;
    public GameViewModel(ELevelType levelType, final WhatChanged whatChanged) {
        gameLogic = new GameLogic(whatChanged);
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
        gameLogic.startTheGame(levelType);
    }

    public void update(float delta) {
        gameLogic.tick(delta);

        if (gameLogic.getIsNewGame()) {
            newGame = true;
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
    public boolean isGameWon() {
        return gameLogic.isGameWon();
    }
    public boolean isGameLost() {
        return gameLogic.isGameLost();
    }

}
