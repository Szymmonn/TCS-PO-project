package io.github.StoneDigger.model.Classes;
import io.github.StoneDigger.model.Interfaces.ILevelStats;

public class LevelStatus implements ILevelStats {
    private int HP;
    private int levelNumber;
    private int diamondCount=0;


    @Override
    public int getDiamondCount() {
        return diamondCount;
    }

    @Override
    public int getHP() {
        return HP;
    }

    @Override
    public int getLevelNumber() {
        return levelNumber;
    }

    @Override
    public void setDiamondCount(int diamondCount) {
        this.diamondCount = diamondCount;
    }

    @Override
    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
}
