package io.github.StoneDigger.model.GameLogic;

import io.github.StoneDigger.model.Boards.BoardGenerators.ELevelType;
import io.github.StoneDigger.model.Boards.IBoard;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.IPlayer;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.Managers.LevelManager;

/*
responsible for game cycle
 */
public class GameLogic {
    LevelManager levelManager;

    public GameLogic() {
        levelManager = new LevelManager();
    }

    public void startTheGame(ELevelType levelType) {
        levelManager.startNewLevel();
    }

    public void tick(float delta) {
        levelManager.tick(delta);
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
}
