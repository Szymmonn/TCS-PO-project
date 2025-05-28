package io.github.StoneDigger.model.Interfaces;

public interface ISelfUpdateManager {
    void updateAll();
    void addToList(ISelfUpdate update);
    void removeFromList(ISelfUpdate update);
}
