package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.IEntity;
import io.github.StoneDigger.model.Level.Managers.BoardManager;

public class ShelterTile extends ATile implements IWalkableTile {
    public ShelterTile(GridPoint2 start, BoardManager boardManager) {
        this.boardManager = boardManager;
        this.position = start;
    }

    @Override public boolean isWalkable(EDirections dir) { return true; }


    @Override
    public void onWalkBy(IEntity entity,EDirections dir) {
    }
}
