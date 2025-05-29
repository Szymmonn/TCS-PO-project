package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.IEntity;

public class DirtTile extends ATile implements IWalkableTile {
    public DirtTile(LevelManager levelManager) {
        super(levelManager);
    }
    public DirtTile(LevelManager levelManager, GridPoint2 start) {super(levelManager,start); }

    @Override public boolean isWalkable(EDirections dir) { return true; }


    @Override
    public void onWalkBy(IEntity entity,EDirections dir) {
        if(entity instanceof Player) {
            this.destroy();
        }
    }
}
