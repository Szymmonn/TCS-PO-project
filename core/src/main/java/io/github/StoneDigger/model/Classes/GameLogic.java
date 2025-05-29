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

    public GameLogic(IBoardGenerator gen, IBoardValidator val, ILevelManager lvl) {
        this.gen = gen;
        this.val = val;
        this.lvl = lvl;
    }

    @Override public void init() {
        lvl.startLevel(1); gameOver = false;
    }

    @Override public void tick() {
        lvl.update();
        if(!val.validate(lvl.getCurrentBoard()))
            gameOver = true;
    }

    @Override public boolean isGameOver() {
        return gameOver;
    }
}
