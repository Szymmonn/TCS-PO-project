package io.github.StoneDigger.model.Interfaces;

public interface ILevelStats {
    int getScore();
    void addScore(int delta);
    boolean isLevelComplete();
}

