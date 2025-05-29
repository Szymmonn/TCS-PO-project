package io.github.StoneDigger.model.Classes.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.model.Interfaces.ILevelManager;

public abstract class ATile {
    protected ILevelManager levelManager;
    protected GridPoint2 position;

    protected ATile(ILevelManager levelManager) {
        this.levelManager = levelManager;
    }

    protected void destroy() {
        levelManager.getCurrentBoard().setTile(position, new EmptyTile(levelManager));
    }

    public GridPoint2 getPosition() {
        return position;
    }

    protected IBoard getBoard() {
        return levelManager.getCurrentBoard();
    }

    public abstract boolean isWalkable(EDirections dir);
}
