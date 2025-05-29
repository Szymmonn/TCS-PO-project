package io.github.StoneDigger.model.GameObjects.Tiles;

import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;
import io.github.StoneDigger.model.Directions.EDirections;

public class BorderTile extends ATile {
    public BorderTile(LevelManager levelManager) {
        super(levelManager);
    }

    @Override public boolean isWalkable(EDirections dir) { return false; }

}
