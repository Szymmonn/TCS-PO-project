package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.ILevelManager;

public class BrickTile extends ATile {
    public BrickTile(ILevelManager levelManager) {
        super(levelManager);
    }

    @Override
    public boolean isWalkable(EDirections dir) {
        return false;
    }
}
