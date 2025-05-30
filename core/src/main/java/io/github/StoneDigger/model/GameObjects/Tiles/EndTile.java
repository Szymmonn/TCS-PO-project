package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.GameObjects.Entities.IEntity;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.*;

public class EndTile extends ATile implements IWalkableTile {
    public EndTile(GridPoint2 start) {super(start);}


    @Override
    public boolean isWalkable(EDirections dir) {
        return true;
    }

    @Override
    public void onWalkBy(IEntity entity, EDirections dir) {
        if(entity instanceof Player) {
            LevelManager.startNewLevel();
        }
    }
}
