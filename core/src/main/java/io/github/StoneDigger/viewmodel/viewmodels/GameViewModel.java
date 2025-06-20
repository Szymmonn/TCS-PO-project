package io.github.StoneDigger.viewmodel.viewmodels;


import io.github.StoneDigger.model.GameLogic.ELevelType;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.model.GameLogic.GameLogic;
import io.github.StoneDigger.model.Interfaces.IOpponent;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Level.LevelStats;

import java.util.List;

public class GameViewModel {
    private final GameLogic gameLogic;
    private boolean newGame = false;
    public GameViewModel(ELevelType levelType, final WhatChanged whatChanged) {
        gameLogic = new GameLogic(whatChanged, levelType);
        startTheGame();
    }

    public IBoard getBoard() {
        return gameLogic.getBoard();
    }
    public Player getPlayer() {
        return gameLogic.getPlayer();
    }
    public List<IOpponent> getOpponents() {
        return gameLogic.getOpponents();
    }

    public LevelStats getLevelStats() { return gameLogic.getLevelStats(); }

    public void startTheGame() {
        gameLogic.startTheGame();
    }

    public void update(float delta) {
        gameLogic.tick(delta);

        if (gameLogic.isNewGame()) {
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
