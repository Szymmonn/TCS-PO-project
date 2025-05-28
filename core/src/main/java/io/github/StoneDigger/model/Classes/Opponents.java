package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;

import java.util.List;

public class Opponents implements ISelfUpdate {
    private List<GridPoint2> position;

    public Opponents(List<GridPoint2> startPosition) {
        position = startPosition;
    }

    public List<GridPoint2> getPosition() {
        return position;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
