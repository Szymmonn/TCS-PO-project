package io.github.StoneDigger.model.GameObjects.Tiles;

import io.github.StoneDigger.model.GameObjects.Entities.IEntity;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.*;

public class EndTile extends ATile implements IWalkableTile {
    public EndTile(LevelManager levelManager) {
        super(levelManager);
    }

    @Override
    public boolean isWalkable(EDirections dir) {
        return false;
    }

    @Override
    public void onWalkBy(IEntity entity, EDirections dir) {
        if(entity instanceof Player) {
            levelManager.resetLevel();
        }
    }
}
