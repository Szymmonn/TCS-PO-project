package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.*;

public class StartTile extends ATile {
    public StartTile(ILevelManager levelManager) {
        super(levelManager);
    }

    @Override public boolean isWalkable(EDirections dir) { return true; }
}
