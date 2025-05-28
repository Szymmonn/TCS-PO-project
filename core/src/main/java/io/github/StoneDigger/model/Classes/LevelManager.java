package io.github.StoneDigger.model.Classes;

import io.github.StoneDigger.model.Interfaces.ILevelManager;

public class LevelManager implements ILevelManager {
    @Override
    public void resetLevel() {
        setNewLevel(0);
    }

    @Override
    public void setNewLevel(int levelNumber) {

    }
}
