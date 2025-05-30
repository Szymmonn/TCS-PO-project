package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Directions.EDirections;

public class BorderTile extends ATile {
    public BorderTile(GridPoint2 start) {super(start);}


    @Override public boolean isWalkable(EDirections dir) { return false; }

}
