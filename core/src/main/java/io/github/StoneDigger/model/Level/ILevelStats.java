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

    float getTimeElapsed();
    void update(float delta);

    void setGameOverTrue();
    void setGameOverFalse();
    void incrementLevelNumber();
    void setLevelNumber(int levelNumber);
    boolean isGameComplete();
    void setIsGameComplete(boolean b);
    boolean getIsGameWon();
    void setIsGameWon(boolean gameWon);
    void resetLevelSTats();
}

