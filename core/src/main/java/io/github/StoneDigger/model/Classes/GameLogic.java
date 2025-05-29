package io.github.StoneDigger.model.Classes;

import io.github.StoneDigger.model.Interfaces.IBoardGenerator;
import io.github.StoneDigger.model.Interfaces.IBoardValidator;
import io.github.StoneDigger.model.Interfaces.IGameLogic;
import io.github.StoneDigger.model.Interfaces.ILevelManager;

public class GameLogic implements IGameLogic {
    private final IBoardGenerator gen;
    private final IBoardValidator val;
    private final ILevelManager lvl;
    private boolean gameOver;

    public GameLogic() {
        this.gen = new BoardGene
        this.val = val;
        this.lvl = lvl;
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
