package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.Level.Managers.LevelManager;

public class DeactivatedEndTile extends ATile implements ISelfUpdate {

    public DeactivatedEndTile(GridPoint2 start) {
        super(start);
    }

    @Override
    public boolean isWalkable(EDirections dir) {
        return false;
    }

    @Override
    public void update(float delta) {
        int diamondsCollected = LevelManager.getStats().getScore();
        int allDiamonds = LevelManager.getStats().getDiamondCount();
        if(diamondsCollected * 2 > allDiamonds) {
            LevelManager.getBoard().setTile(position, new EndTile(position));
        }
    }
}
