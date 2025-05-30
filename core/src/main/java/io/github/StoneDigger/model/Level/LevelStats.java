package io.github.StoneDigger.model.Level;

import java.time.Duration;
import java.time.LocalTime;

import static java.lang.System.exit;

public class LevelStats implements ILevelStats {
    private int score;
    private int diamondCount;
    private int hp;
    private int levelNumber;
    private LocalTime startTime;

    public LevelStats() {
        this(0,3,1);
    }

    public LevelStats(int diamondCount, int hp, int levelNumber) {
        score = 0;
        this.diamondCount = diamondCount;
        this.hp = hp;
        this.levelNumber = levelNumber;
        this.startTime = LocalTime.now();
    }

    @Override
    public int getHP() {
        return hp;
    }
    @Override
    public void setHP(int HP) {
        this.hp = HP;
    }
    @Override
    public void decreaseHP() {hp --; }

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
    public void setScore(int delta) { score = delta; }
    @Override
    public void collectDiamond() {score ++;}

    @Override
    public int getDiamondCount() { return diamondCount;}
    @Override
    public void setDiamondCount(int n) {diamondCount = n;}
}
