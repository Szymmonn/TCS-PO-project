package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;
import io.github.StoneDigger.model.Directions.EDirections;

public class BorderTile extends ATile {
    public BorderTile(LevelManager levelManager) {
        super(levelManager);
    }
    public BorderTile(LevelManager levelManager, GridPoint2 start) {super(levelManager, start);}


    @Override public boolean isWalkable(EDirections dir) { return false; }

}
