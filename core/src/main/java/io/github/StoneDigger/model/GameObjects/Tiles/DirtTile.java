package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.IWalkableTile;
import io.github.StoneDigger.model.Level.Managers.BoardManager;

public class DirtTile extends ATile implements IWalkableTile, IDestructable {
    public DirtTile(GridPoint2 start, BoardManager boardManager) {
        this.boardManager = boardManager;
        this.position = start;
    }

    @Override public boolean isWalkable(EDirections dir) { return true; }


    @Override
    public void onWalkBy(IEntity entity,EDirections dir) {
        if(entity instanceof Player) {
            this.destroy();
        }
    }
}
