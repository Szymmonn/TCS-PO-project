package io.github.StoneDigger.model.Classes.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.*;

public class DiamondTile implements ITile, ISelfUpdate {
    @Override public boolean isWalkable() { return true; }

    @Override
    public void onWalkBy(IEntity entity) {

    }

    @Override
    public void update() {

    }
}
