package io.github.StoneDigger.model.Classes.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;
import io.github.StoneDigger.model.Interfaces.ITile;

public class DiamondTile implements ITile, ISelfUpdate {

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public GridPoint2 getPosition() {
        return null;
    }

    @Override
    public void setPosition(GridPoint2 newPosition) {

    }

    @Override
    public boolean tryToMove(EDirections directions) {
        return false;
    }
}
