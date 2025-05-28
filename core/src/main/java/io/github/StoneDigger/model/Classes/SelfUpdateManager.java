package io.github.StoneDigger.model.Classes;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;
import io.github.StoneDigger.model.Interfaces.ISelfUpdateManager;

import java.util.ArrayList;

public class SelfUpdateManager implements ISelfUpdateManager {
    private final ArrayList<ISelfUpdate> selfUpdateList;

    public SelfUpdateManager() {
        this.selfUpdateList = new ArrayList<>();
    }

    @Override
    public void updateAll() {
        for(ISelfUpdate tmp : selfUpdateList) {
            tmp.update();
        }
    }

    @Override
    public void addToList(ISelfUpdate update) {
        selfUpdateList.add(update);
    }

    @Override
    public void removeFromList(ISelfUpdate update) {
        selfUpdateList.remove(update);
    }
}
