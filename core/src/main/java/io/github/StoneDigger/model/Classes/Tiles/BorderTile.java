package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.ITile;

public class BorderTile implements ITile {
    @Override
    public boolean tryVisit(IEntity entity, EDirections direction) {
        return false;
    }
}
