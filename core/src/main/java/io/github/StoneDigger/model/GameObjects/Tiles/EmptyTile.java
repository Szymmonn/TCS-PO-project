package io.github.StoneDigger.model.GameObjects.Tiles;

import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;
import io.github.StoneDigger.model.Directions.*;

public class EmptyTile extends ATile {
    public EmptyTile(LevelManager levelManager) {
        super(levelManager);
    }

    @Override public boolean isWalkable(EDirections dir) { return true; }
}
