package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;

public class DeactivatedEndTile extends ATile implements ISelfUpdate {
    private final LevelStats levelStats;
    private final UpdateManager updateManager;
    private final LevelManager levelManager;

    public DeactivatedEndTile(GridPoint2 start, BoardManager boardManager, LevelStats levelStats, UpdateManager updateManager, LevelManager levelManager) {
        this.boardManager = boardManager;
        this.position = start;
        this.levelStats = levelStats;
        this.updateManager = updateManager;
        this.levelManager = levelManager;
    }

    @Override
    public boolean isWalkable(EDirections dir) {
        return false;
    }

    @Override
    public void update(float delta) {
        int diamondsCollected = levelStats.getScore();
        int allDiamonds = levelStats.getDiamondCount();
        if(diamondsCollected * 2 > allDiamonds) {
            updateManager.removeFromUpdates(this);
            boardManager.setTile(position, new EndTile(position, boardManager, levelStats, levelManager));
        }
    }
}
