package io.github.StoneDigger.model.GameObjects;

public interface ISelfUpdateManager {
    void register(ISelfUpdate obj);
    void unregister(ISelfUpdate obj);
    void updateAll();
}

