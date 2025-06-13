package io.github.StoneDigger.model.GameLogic;

import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Interfaces.IOpponent;
import io.github.StoneDigger.model.Interfaces.IPlayer;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

import java.util.List;

/**
 * Responsible for managing the main game cycle and state transitions.
 */
public class GameLogic {
    private boolean newGame = false;
    private final LevelManager levelManager;

    public GameLogic(final WhatChanged whatChanged, ELevelType levelType) {
        this.levelManager = new LevelManager(whatChanged, levelType);
    }

    public void startTheGame() {
        newGame = false;
        levelManager.startNewLevel();
    }

    public void tick(float delta) {
        levelManager.tick(delta);

        if (levelManager.getLevelStats().isGameComplete()) {
            newGame = true;
            levelManager.startNewLevel();
        }
    }

    public boolean isNewGame() {
        return newGame;
    }

    public List<IOpponent> getOpponents() {
        return levelManager.getOpponents();
    }

    public IPlayer getPlayer() {
        return levelManager.getPlayer();
    }

    public IBoard getBoard() {
        return levelManager.getBoard();
    }

    public LevelStats getLevelStats() {
        return levelManager.getLevelStats();
    }

    public void movePlayer(EDirections direction) {
        levelManager.movePlayer(direction);
    }

    public boolean isGameLost() {
        return levelManager.isGameLost();
    }

    public boolean isGameWon() {
        return levelManager.isGameWon();
    }
}
