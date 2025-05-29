package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.*;

public class EmptyTile extends ATile implements IWalkableTile {
    @Override public boolean isWalkable() { return true; }


    @Override
    public void onWalkBy(IEntity entity) {

    }
}
