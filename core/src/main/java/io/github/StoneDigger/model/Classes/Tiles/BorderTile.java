package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.model.Interfaces.ITile;

public class BorderTile extends ATile {
    @Override public boolean isWalkable() { return false; }

}
