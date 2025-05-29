package io.github.StoneDigger.model.Classes;

import io.github.StoneDigger.model.Interfaces.ILevelStats;

import java.time.Duration;
import java.time.LocalTime;

public class LevelStats implements ILevelStats {
    private int score;
    private int diamondCount;
    private int HP;
    private int levelNumber;
    private LocalTime startTime;

    @Override
    public int getHP() {
        return HP;
    }
    @Override
    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public Duration getTimeElapsed() {return Duration.between(startTime, LocalTime.now());}
    @Override
    public void setStartTime(LocalTime startTime) {this.startTime = startTime;}

    @Override
    public int getLevelNumber() {
        return levelNumber;
    }

    @Override
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    @Override
    public int getScore() { return score; }
    @Override
    public void setScore(int delta) { score += delta; }

    @Override
    public int getDiamondCount() { return diamondCount;}
    @Override
    public void setDiamondCount(int n) {diamondCount = n;}
}
