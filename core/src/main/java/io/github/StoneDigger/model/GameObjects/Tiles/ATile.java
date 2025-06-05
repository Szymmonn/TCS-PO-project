package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;

public abstract class ATile {
    protected GridPoint2 position;

    protected ATile(GridPoint2 start) {this.position = start;}

    protected void destroy() {
//        UpdateManager.removedFromUpdates((ISelfUpdate) this);
        LevelManager.getBoard().setTile(position, new EmptyTile(position));
    }

    public GridPoint2 getPosition() {
        return position;
    }
    public void setPosition(GridPoint2 pos) {
        position = pos;
    }

    protected Board getBoard() {
        return LevelManager.getBoard();
    }

    public abstract boolean isWalkable(EDirections dir);
}
