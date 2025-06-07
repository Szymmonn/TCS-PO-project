package io.github.StoneDigger.model.GameLogic;

import io.github.StoneDigger.model.Boards.BoardGenerators.ELevelType;
import io.github.StoneDigger.model.Boards.IBoard;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.IOpponent;
import io.github.StoneDigger.model.Interfaces.IPlayer;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

/*
responsible for game cycle
 */
public class GameLogic {
    private final WhatChanged whatChanged;
    private boolean newGame = false;
    private boolean isGameWon = false;
    LevelManager levelManager;
    ELevelType levelType;

    public GameLogic(final WhatChanged whatChanged) {
        this.whatChanged = whatChanged;
        levelManager = new LevelManager(whatChanged);
    }

    public void startTheGame(ELevelType levelType) {
        newGame = false;
        levelManager.startNewLevel(levelType);
    }

    public void tick(float delta) {
        levelManager.tick(delta);
        if(levelManager.getLevelStats().isGameComplete()) {
            newGame = true;
            levelManager.startNewLevel(levelType);
        }

        if(levelManager.getLevelStats().getIsGameWon()) {
            isGameWon = true;
            //TODO: implement logic after finishing the game
        }
    }

    public boolean getIsNewGame() {return newGame;}

    public IOpponent getOpponent() {
        return levelManager.getOpponent();
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
    public void moveOpponent(EDirections direction) {
        levelManager.moveOpponent(direction);
    }

    public ELevelType getLevelType() {return levelType;}
    public void setLevelType(ELevelType levelType) {this.levelType = levelType;}
}
