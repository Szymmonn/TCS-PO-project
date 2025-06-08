package io.github.StoneDigger.model.GameLogic;

import io.github.StoneDigger.model.Boards.IBoard;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Interfaces.IOpponent;
import io.github.StoneDigger.model.Interfaces.IPlayer;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

import java.util.List;

/*
responsible for game cycle
 */
public class GameLogic {
    private boolean newGame = false;
    LevelManager levelManager;

    public GameLogic(final WhatChanged whatChanged, ELevelType levelType) {
        levelManager = new LevelManager(whatChanged, levelType);
    }

    public void startTheGame() {
        newGame = false;
        levelManager.startNewLevel();
    }

    public void tick(float delta) {
        levelManager.tick(delta);


        if(levelManager.getLevelStats().isGameComplete()) {
            newGame = true;
            levelManager.startNewLevel();
        }
    }

    public boolean getIsNewGame() {return newGame;}

    public List<IOpponent> getOpponents() {
        return levelManager.getOpponents();
    }
    public IPlayer getPlayer() {
        return levelManager.getPlayer();
    }
    public IBoard getBoard() {
        return levelManager.getBoard();
    }
    public ILevelStats getLevelStats() {
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
