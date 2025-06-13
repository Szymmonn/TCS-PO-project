package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.IWalkableTile;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.*;

public class EndTile extends ATile implements IWalkableTile {
    LevelStats levelStats;
    LevelManager levelManager;
    boolean active;

    public EndTile(GridPoint2 start, BoardManager boardManager, LevelStats levelStats, LevelManager levelManager) {
        this.boardManager = boardManager;
        this.position = start;
        this.levelStats = levelStats;
        this.levelManager = levelManager;
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    @Override
    public boolean isWalkable(EDirections dir) {
        return true;
    }

    @Override
    public void onWalkBy(IEntity entity, EDirections dir) {
        if(entity instanceof Player && active) {
            levelStats.setIsGameComplete(true);
        }
    }
}
