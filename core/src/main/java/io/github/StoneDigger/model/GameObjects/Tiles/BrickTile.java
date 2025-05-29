package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;
import io.github.StoneDigger.model.Directions.EDirections;

public class BrickTile extends ATile {
    public BrickTile(GridPoint2 start) {super(start);}


    @Override
    public boolean isWalkable(EDirections dir) {
        return false;
    }
}
