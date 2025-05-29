package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Classes.Player;
import io.github.StoneDigger.model.Interfaces.*;

public class EndTile extends ATile implements IWalkableTile {
    public EndTile(ILevelManager levelManager) {
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
