package io.github.StoneDigger.model.Interfaces;

public interface ISelfUpdateManager {
    void register(ISelfUpdate obj);
    void unregister(ISelfUpdate obj);
    void updateAll();
}

