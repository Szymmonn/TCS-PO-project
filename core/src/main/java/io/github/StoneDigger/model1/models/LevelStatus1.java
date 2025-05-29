package io.github.StoneDigger.model1.models;

import java.time.Duration;
import java.time.LocalTime;

public class LevelStatus1 {
    private int HP = 3;
    private int diamondCount = 20;
    private int diamondCollected = 12;
    private int levelNumber = 10;
    private LocalTime startTime = LocalTime.now();

    public LevelStatus1 () {}
    public int getDiamondCount() {
        return diamondCount;
    }
    private int getDiamondCollected() {return diamondCollected;}
    public int getHP() {
        return HP;
    }
    public int getLevelNumber() {
        return levelNumber;
    }
    public void setDiamondCount(int diamondCount) {
        this.diamondCount = diamondCount;
    }
    public void setDiamondCollected(int diamondCollected) {this.diamondCollected = diamondCollected; };
    public void setHP(int HP) {
        this.HP = HP;
    }
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
    public void setStartTime(LocalTime startTime) {this.startTime = startTime; }
    public Duration timeElapsed() {return Duration.between(startTime, LocalTime.now()); }

}
