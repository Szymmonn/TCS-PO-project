package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.EDirections;

import java.util.HashMap;

public class Player {
    private GridPoint2 position;

    public Player(GridPoint2 startPosition) {
        position = startPosition;
    }

    public void moveTo(EDirections direction) {
    }

    public GridPoint2 getPosition() {
        return position;
    }

}
