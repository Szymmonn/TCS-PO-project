package io.github.StoneDigger.model.Interfaces;

public interface ILevelStats {
    int getScore();
    void addScore(int delta);
    public int getLevelNumber();
    void setLevelNumber(int levelNumber);
    int getHP();
    void setHP(int hp);
}

