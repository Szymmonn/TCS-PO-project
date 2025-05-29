package io.github.StoneDigger.model.Level.LevelManagement;

import io.github.StoneDigger.model.GameObjects.ISelfUpdate;

import java.util.Collection;
import java.util.HashSet;

public abstract class UpdateManager {
    private static final Collection<ISelfUpdate> selfUpdates = new HashSet<>();

    public static void addToUpdates(ISelfUpdate selfUpdate) {
        selfUpdates.add(selfUpdate);
    }
    public static void removedFromUpdates(ISelfUpdate selfUpdate) {
        selfUpdates.remove(selfUpdate);
    }
    public static void updateAll(float delta) {
        for(ISelfUpdate selfUpdate : selfUpdates) selfUpdate.update(delta);
    }
}
