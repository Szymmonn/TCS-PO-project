package io.github.StoneDigger.model.Interfaces;

public interface ILevelManager {
    void resetLevel();
    void startLevel(int index);
    IBoard getCurrentBoard();
    ILevelStats getStats();
    void update();
}
