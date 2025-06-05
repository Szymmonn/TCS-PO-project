package io.github.StoneDigger.model.Level;

import io.github.StoneDigger.model.GameObjects.ISelfUpdate;

import java.time.Duration;
import java.time.LocalTime;

public interface ILevelStats {
    int getScore();
    void setScore(int score);
    void collectDiamond();

    int getDiamondCount();
    void setDiamondCount(int n);

    int getLevelNumber();

    int getHP();
    void setHP(int hp);
    void decreaseHP();

    Duration getTimeElapsed();
    public void setGameOverTrue();
    public void setGameOverFalse();
    public void incrementLevelNumber();
    public void setLevelNumber(int levelNumber);
}

