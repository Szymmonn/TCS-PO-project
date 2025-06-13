package io.github.StoneDigger.model.Level.Managers;

import io.github.StoneDigger.model.Interfaces.IOpponent;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;

import java.awt.geom.IllegalPathStateException;
import java.util.ArrayList;
import java.util.List;

public class UpdateManager {
    private final List<ISelfUpdate> selfUpdates = new ArrayList<>();
    private PlayerManager playerManager;

    public void addToUpdates(ISelfUpdate selfUpdate) {

        selfUpdates.add(selfUpdate);
    }

    public void addPlayerManager(PlayerManager playerManager) {
        this.playerManager =  playerManager;
    }

    public void removedFromUpdates(ISelfUpdate selfUpdate) {
        selfUpdates.remove(selfUpdate);
    }

    public void updateAll(float delta) {

        List<ISelfUpdate> snapshot = new ArrayList<>(selfUpdates);
        for(ISelfUpdate selfUpdate : snapshot) {
            selfUpdate.update(delta);
        }
        playerManager.getPlayer().update(delta);
    }

    public void clearAll() {
        selfUpdates.clear();
    }
}
