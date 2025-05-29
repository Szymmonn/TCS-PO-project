package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Classes.Player;
import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.ILevelManager;
import io.github.StoneDigger.model.Interfaces.IWalkableTile;

public class DirtTile extends ATile implements IWalkableTile {
    public DirtTile(ILevelManager levelManager) {
        super(levelManager);
    }

    @Override public boolean isWalkable(EDirections dir) { return true; }


    @Override
    public void onWalkBy(IEntity entity,EDirections dir) {
        if(entity instanceof Player) {
            this.destroy();
        }
    }
}
