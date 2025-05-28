package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.*;

import java.util.List;

public class RockTile implements ITile, IPlayerKiller, ISelfUpdate {
    private List<RockTile> rockList;

    @Override
    public boolean tryVisit(IEntity entity, EDirections direction) {
        return false;
    }

    @Override
    public boolean tryToKill() {
//        if();
        return false;
    }

    @Override
    public void setLevelStats() {

    }

    @Override
    public boolean update() {
        return false;
    }
    //dwayne
}
