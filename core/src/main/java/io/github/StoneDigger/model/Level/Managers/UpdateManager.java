package io.github.StoneDigger.model.Level.Managers;

import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.GameObjects.Tiles.RockTile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class UpdateManager {
    private final List<ISelfUpdate> selfUpdates = new ArrayList<>();

    public void addToUpdates(ISelfUpdate selfUpdate) {

        selfUpdates.add(selfUpdate);
    }

    public void removedFromUpdates(ISelfUpdate selfUpdate) {
        selfUpdates.remove(selfUpdate);
    }

    public void updateAll(float delta) {
        List<ISelfUpdate> snapshot = new ArrayList<>(selfUpdates);
        for(ISelfUpdate selfUpdate : snapshot) selfUpdate.update(delta);
    }

    public void clearAll() {
        selfUpdates.clear();
    }
}
