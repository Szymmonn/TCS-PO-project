package io.github.StoneDigger.model.Interfaces;
import io.github.StoneDigger.model.Classes.LevelStatus;

//ma distep do klasy iLevelStats
//ma klasy jak end, diamond, playerKiller


public interface IStatsChanger {
    public static final LevelStatus sLevelStatus = new LevelStatus();
    public void setLevelStats();
}
