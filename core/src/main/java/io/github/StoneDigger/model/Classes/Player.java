package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.IEntity;

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
//        if()
    }

}
