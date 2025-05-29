package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.ITile;

public class BorderTile implements ITile {
    @Override public boolean isWalkable() { return false; }

    @Override
    public void onWalkBy(IEntity entity) {

    }
}
