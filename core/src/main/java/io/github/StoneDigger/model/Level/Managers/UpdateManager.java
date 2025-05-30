package io.github.StoneDigger.model.Level.Managers;

import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.GameObjects.Tiles.RockTile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public abstract class UpdateManager {
    private static final List<ISelfUpdate> selfUpdates = new ArrayList<>();

    public static void addToUpdates(ISelfUpdate selfUpdate) {
        selfUpdates.add(selfUpdate);
    }
    public static void removedFromUpdates(ISelfUpdate selfUpdate) {
        selfUpdates.remove(selfUpdate);
    }
    public static void updateAll(float delta) {
        List<ISelfUpdate> snapshot = new ArrayList<>(selfUpdates);
        for(ISelfUpdate selfUpdate : snapshot) selfUpdate.update(delta);
    }
}
