package io.github.StoneDigger.model.Level.Managers;

import io.github.StoneDigger.model.Interfaces.ISelfUpdate;

import java.util.ArrayList;
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

    public void opponentClear() {

    }

    public void clearAll() {
        selfUpdates.clear();
    }
}
