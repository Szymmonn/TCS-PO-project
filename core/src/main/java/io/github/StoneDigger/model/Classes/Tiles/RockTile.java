package io.github.StoneDigger.model.Classes.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.*;

public class RockTile implements ITile {
    @Override public boolean isWalkable() { return false; }
}
