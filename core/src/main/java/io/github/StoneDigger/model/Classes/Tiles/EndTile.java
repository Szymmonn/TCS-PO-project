package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.IMovable;
import io.github.StoneDigger.model.Interfaces.ITile;

public class EndTile implements ITile {
    @Override
    public boolean canItMoveOnMySpot(IMovable object, EDirections directions) {
        return true;
    }
}
