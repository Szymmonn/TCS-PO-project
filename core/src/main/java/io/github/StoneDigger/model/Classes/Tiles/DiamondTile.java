package io.github.StoneDigger.model.Classes.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.*;

public class DiamondTile implements ITile, ISelfUpdate, ICollectable {

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

    @Override
    public void collect() {

    }

    @Override
    public void setLevelStats() {

    }
}
