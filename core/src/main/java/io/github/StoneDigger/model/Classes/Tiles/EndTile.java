package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.*;

public class EndTile implements ITile, IWalkableTile {
    @Override public boolean isWalkable() { return true; }

    @Override
    public void onWalkBy(IEntity entity) {

    }
}
