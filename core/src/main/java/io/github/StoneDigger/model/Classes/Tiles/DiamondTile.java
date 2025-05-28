package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;
import io.github.StoneDigger.model.Interfaces.ITile;

public class DiamondTile implements ITile, ISelfUpdate {
    @Override
    public boolean tryVisit(IEntity entity, EDirections direction) {
        return true;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
