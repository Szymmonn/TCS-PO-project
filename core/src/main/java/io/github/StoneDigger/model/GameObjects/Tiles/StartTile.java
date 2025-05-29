package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;
import io.github.StoneDigger.model.Directions.*;

public class StartTile extends ATile {
    public StartTile(GridPoint2 start) {super(start);}


    @Override public boolean isWalkable(EDirections dir) { return true; }
}
