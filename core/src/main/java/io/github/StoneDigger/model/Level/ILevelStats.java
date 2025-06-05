package io.github.StoneDigger.model.Level;

import java.time.Duration;

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
    public boolean isGameComplete();
    public void setIsGameComplete(boolean b);
    public boolean getIsGameWon();
    public void setIsGameWon(boolean gameWon);
}

