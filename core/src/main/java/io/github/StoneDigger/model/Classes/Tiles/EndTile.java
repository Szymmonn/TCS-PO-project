package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Classes.Player;
import io.github.StoneDigger.model.Interfaces.*;

public class EndTile extends ATile implements IWalkableTile {
    @Override
    protected boolean isWalkable() {
        return false;
    }

    @Override
    public void onWalkBy(IEntity entity) {
        if(entity instanceof Player) {
            levelManager.resetLevel();
        }
    }
}
