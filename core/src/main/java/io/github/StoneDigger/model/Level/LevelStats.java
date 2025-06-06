package io.github.StoneDigger.model.Level;

import java.time.Duration;
import java.time.LocalTime;

public class LevelStats implements ILevelStats {
    private int score;
    private int diamondCount;
    private int hp;
    private int levelNumber;
    private LocalTime startTime;
    private boolean gameOver;
    private boolean isGameComplete;
    private boolean isGameWon;

    public LevelStats() {
        this(0,3,1);
    }

    public LevelStats(int diamondCount, int hp, int levelNumber) {
        score = 0;
        this.diamondCount = diamondCount;
        this.hp = hp;
        this.levelNumber = levelNumber;
        this.startTime = LocalTime.now();
        gameOver = false;
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
    public void decreaseHP() {
        if (hp <= 0) hp = 0;
        hp--;
    }

    @Override
    public Duration getTimeElapsed() {return Duration.between(startTime, LocalTime.now());}

    @Override
    public int getLevelNumber() {
        return levelNumber;
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
    public boolean isGameOver() {return gameOver;}
    public void setGameOverTrue() {gameOver = true;}
    public void setGameOverFalse() {gameOver = false;}
    public void incrementLevelNumber() {levelNumber++;}
    public void setLevelNumber(int levelNumber) {this.levelNumber = levelNumber;}
    public boolean isGameComplete() {return isGameComplete;}
    public void setIsGameComplete(boolean isGameComplete) {this.isGameComplete = isGameComplete;}
    public boolean getIsGameWon() {return isGameWon;}
    public void setIsGameWon(boolean gameWon) {this.isGameWon = gameWon;}

    public void resetLevelSTats() {
        this.startTime = LocalTime.now();
        setHP(3); //TODO: make it not always 3
        setScore(0);
        setDiamondCount(0);
        setIsGameWon(false);
        setIsGameComplete(false);
    }
}
