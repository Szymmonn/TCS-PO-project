package io.github.StoneDigger.model.Interfaces;

import java.time.Duration;
import java.time.LocalTime;

public interface ILevelStats {
    int getScore();
    void setScore(int delta);

    int getDiamondCount();
    void setDiamondCount(int n);

    int getLevelNumber();
    void setLevelNumber(int levelNumber);

    int getHP();
    void setHP(int hp);

    Duration getTimeElapsed();
    void setStartTime(LocalTime startTime);
}

