package io.github.StoneDigger.model.Level.LevelManagement;

import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Level.ILevelStats;

public interface ILevelManager {
    void resetLevel();
    void startLevel(int index);
    Board getCurrentBoard();
    ILevelStats getStats();
    void update();
}
