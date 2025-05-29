package io.github.StoneDigger.model.Level;

import java.time.Duration;
import java.time.LocalTime;

public class LevelStats implements ILevelStats {
    private int score;
    private int HP;
    private int levelNumber;
    private LocalTime startTime;
    public int getHP() {
        return HP;
    }
    public int getLevelNumber() {
        return levelNumber;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
    public void setStartTime(LocalTime startTime) {this.startTime = startTime; }
    public Duration timeElapsed() {return Duration.between(startTime, LocalTime.now()); }
    @Override public int getScore() { return score; }
    @Override public void addScore(int delta) { score += delta; }
}
