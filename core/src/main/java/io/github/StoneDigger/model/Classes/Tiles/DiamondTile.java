package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Classes.Player;
import io.github.StoneDigger.model.Interfaces.*;

public class DiamondTile extends ATile implements IWalkableTile {
    public DiamondTile(ILevelManager levelManager) {
        super(levelManager);
    }

    @Override public boolean isWalkable(EDirections dir) {
        return true;
    }

    @Override
    public void onWalkBy(IEntity entity, EDirections dir) {
        if(entity instanceof Player) {
            levelManager.getStats().addScore(1);
            this.destroy();
        }
    }
}
