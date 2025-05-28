package io.github.StoneDigger.model.Classes.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.*;

public class RockTile implements ITile, IKiller, ISelfUpdate {
    private GridPoint2 rockPosition;

    @Override
    public boolean tryToKill() {
//        if();
        return false;
    }


    @Override
    public boolean update() {
        return false;
    }

    @Override
    public GridPoint2 getPosition() {
        return rockPosition;
    }

    @Override
    public void setPosition(GridPoint2 newPosition) {
        rockPosition = newPosition;
    }

    @Override
    public boolean tryToMove(EDirections directions) {
        return false;
    }
    //dwayne
}
