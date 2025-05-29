package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;
import io.github.StoneDigger.model.Directions.EDirections;

public abstract class ATile {
    protected GridPoint2 position;

    protected ATile(GridPoint2 start) {this.position = start;}

    protected void destroy() {
        LevelManager.getBoard().setTile(position, new EmptyTile(position));
    }

    public GridPoint2 getPosition() {
        return position;
    }

    protected Board getBoard() {
        return LevelManager.getBoard();
    }

    public abstract boolean isWalkable(EDirections dir);
}
