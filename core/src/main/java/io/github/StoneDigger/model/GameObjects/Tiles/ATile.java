package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Directions.EDirections;

public abstract class ATile {
    protected GridPoint2 position;
    protected BoardManager boardManager;

    protected void destroy() {
        boardManager.getBoard().setTile(position, new EmptyTile(position, boardManager));
    }

    public GridPoint2 getPosition() {
        return position;
    }

    public abstract boolean isWalkable(EDirections dir);
}
