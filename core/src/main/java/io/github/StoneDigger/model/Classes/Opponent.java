package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.IPlayerKiller;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;

public class Opponent implements IEntity, IPlayerKiller, ISelfUpdate {
    private GridPoint2 currentPosition;

    public Opponent() {
        currentPosition = new GridPoint2();
    }

    public Opponent(GridPoint2 startPosition) {
        this.currentPosition = startPosition;
    }

    public GridPoint2 getPosition() {
        return currentPosition;
    }

    public void setPosition(GridPoint2 newPosition) {
        this.currentPosition = newPosition;
    }

    @Override
    public boolean tryToMove(EDirections directions) {
        return false;
    }

    @Override
    public boolean tryToKill() {
        return false;
    }

    @Override
    public void setLevelStats() {

    }

    @Override
    public boolean update() {
        return false;
    }
}
