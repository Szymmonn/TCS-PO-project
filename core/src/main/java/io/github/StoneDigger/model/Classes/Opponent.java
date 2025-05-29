package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.*;

public class Opponent implements IEntity, IPredator, ISelfUpdate {
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
    public boolean update() {
        return false;
    }

    @Override
    public boolean canItMoveOnMySpot(IMovable object, EDirections directions) {
        return false;
    }
}
