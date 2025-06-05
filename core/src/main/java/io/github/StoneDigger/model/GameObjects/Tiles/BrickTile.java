package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Level.Managers.BoardManager;

public class BrickTile extends ATile {

    public BrickTile(GridPoint2 start, BoardManager boardManager) {
        this.boardManager = boardManager;
        this.position = start;
    }

    @Override
    public boolean isWalkable(EDirections dir) {
        return false;
    }
}
