package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.IMovable;

import java.util.HashMap;

public class Player implements IEntity {
    private GridPoint2 position;

    public Player(GridPoint2 startPosition) {
        position = startPosition;
    }

    public GridPoint2 getPosition() {
        return position;
    }

    public void setPosition(GridPoint2 newPosition) {
        this.position = newPosition;
    }

    @Override
    public boolean tryToMove(EDirections directions) {
        GridPoint2 vector = new GridPoint2();
        switch(directions) {
            case RIGHT: vector.x++;
            case LEFT: vector.x++;
            case UP: vector.x++;
            case DOWN: vector.x++;
        }
        //GridPoint2 newPosition = new GridPoint2(position.x+directions.x)
        //if()
        return true;
    }

    @Override
    public boolean canItMoveOnMySpot(IMovable object, EDirections directions) {
        return false;
    }
}
