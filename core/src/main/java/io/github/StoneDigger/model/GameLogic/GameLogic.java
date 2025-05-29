package io.github.StoneDigger.model.GameLogic;

import io.github.StoneDigger.model.Level.LevelManagement.ILevelManager;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;

public class GameLogic implements IGameLogic {
    private final ILevelManager lvl;
    private boolean gameOver;

    public GameLogic() {
        this.lvl = new LevelManager();
    }

    @Override public void init() {
        lvl.startLevel(1); gameOver = false;
    }

    @Override public void tick() {
        lvl.update();
    }

    @Override public boolean isGameOver() {
        return gameOver;
    }
}
