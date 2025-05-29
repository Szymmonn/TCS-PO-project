package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.ILevelManager;

public class BorderTile extends ATile {
    public BorderTile(ILevelManager levelManager) {
        super(levelManager);
    }

    @Override public boolean isWalkable(EDirections dir) { return false; }

}
