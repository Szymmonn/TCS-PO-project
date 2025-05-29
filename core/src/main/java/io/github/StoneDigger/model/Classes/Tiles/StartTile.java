package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Classes.LevelManager;
import io.github.StoneDigger.model.Interfaces.*;

public class StartTile implements ITile, IWalkableTile {
    IBoard board;
    @Override public boolean isWalkable() { return true; }

    @Override
    public void onWalkBy(IEntity entity) {

    }
}
