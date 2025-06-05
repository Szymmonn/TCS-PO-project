package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.GameObjects.Entities.IEntity;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.*;

public class EndTile extends ATile implements IWalkableTile {
    ILevelStats levelStats;
    LevelManager levelManager;

    public EndTile(GridPoint2 start, BoardManager boardManager, ILevelStats levelStats, LevelManager levelManager) {
        this.boardManager = boardManager;
        this.position = start;
        this.levelStats = levelStats;
        this.levelManager = levelManager;
    }

    @Override
    public boolean isWalkable(EDirections dir) {
        return true;
    }

    @Override
    public void onWalkBy(IEntity entity, EDirections dir) {
        if(entity instanceof Player) {
            //levelStats.setGameOverTrue();
            levelManager.startNewLevel();
        }
    }
}
