package io.github.StoneDigger.model.Level;


public class LevelStats {
    private int score;
    private int diamondCount;
    private int hp;
    private int levelNumber;
    private float timePassed;
    private boolean isGameComplete;
    private boolean isGameWon;

    public LevelStats() {
        this(0,3,0);
    }

    public LevelStats(int diamondCount, int hp, int levelNumber) {
        score = 0;
        this.diamondCount = diamondCount;
        this.hp = hp;
        this.levelNumber = levelNumber;
        this.timePassed = 0;
    }


    public int getHP() {
        return hp;
    }


    public void setHP(int HP) {
        this.hp = HP;
    }


    public void decreaseHP() {
        if (hp <= 0) hp = 0;
        hp--;
    }


    public float getTimeElapsed() {return timePassed; }

    public void update(float delta) {timePassed += delta;}


    public int getLevelNumber() {
        return levelNumber;
    }


    public int getScore() { return score; }

    public void setScore(int delta) { score = delta; }

    public void collectDiamond() {score ++;}

    public int getDiamondCount() { return diamondCount;}
    public void setDiamondCount(int n) {diamondCount = n;}

    public void incrementLevelNumber() {levelNumber++;}
    public void setLevelNumber(int levelNumber) {this.levelNumber = levelNumber;}
    public boolean isGameComplete() {return isGameComplete;}
    public void setIsGameComplete(boolean isGameComplete) {this.isGameComplete = isGameComplete;}
    public boolean getIsGameWon() {return isGameWon;}
    public void setIsGameWon(boolean gameWon) {this.isGameWon = gameWon;}

    public void resetLevelSTats() {
        this.timePassed = 0;
        setHP(3); //TODO: make it not always 3
        setScore(0);
        setDiamondCount(0);
        setIsGameWon(false);
        setIsGameComplete(false);
    }
}
