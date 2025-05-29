package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;
import io.github.StoneDigger.model.Directions.EDirections;

public abstract class ATile {
    protected LevelManager levelManager;
    protected GridPoint2 position;

    protected ATile(LevelManager levelManager) {
        this.levelManager = levelManager;
    }
    protected ATile(LevelManager levelManager, GridPoint2 start) {this(levelManager); this.position = start;}

    protected void destroy() {
        levelManager.getCurrentBoard().setTile(position, new EmptyTile(levelManager));
    }

    public GridPoint2 getPosition() {
        return position;
    }

    protected Board getBoard() {
        return levelManager.getCurrentBoard();
    }

    public abstract boolean isWalkable(EDirections dir);
}
