package io.github.StoneDigger.model.Classes;

import io.github.StoneDigger.model.Classes.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Interfaces.IBoardGenerator;
import io.github.StoneDigger.model.Interfaces.IBoardValidator;
import io.github.StoneDigger.model.Interfaces.IGameLogic;
import io.github.StoneDigger.model.Interfaces.ILevelManager;

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
