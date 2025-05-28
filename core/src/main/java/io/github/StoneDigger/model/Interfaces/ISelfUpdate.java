package io.github.StoneDigger.model.Interfaces;
import io.github.StoneDigger.model.Classes.SelfUpdateManager;

public interface ISelfUpdate extends IMovable {
    SelfUpdateManager sSelfUpdateManager = new SelfUpdateManager();
    boolean update();
    default void start() {
        sSelfUpdateManager.addToList(this);
    }

    default void stop() {
        sSelfUpdateManager.removeFromList(this);
    }
}
