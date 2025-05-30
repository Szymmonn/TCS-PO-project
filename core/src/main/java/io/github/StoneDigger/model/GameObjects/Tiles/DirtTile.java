package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.IEntity;

public class DirtTile extends ATile implements IWalkableTile {
    public DirtTile(GridPoint2 start) {super(start);}

    @Override public boolean isWalkable(EDirections dir) { return true; }


    @Override
    public void onWalkBy(IEntity entity,EDirections dir) {
        if(entity instanceof Player) {
            this.destroy();
        }
    }
}
