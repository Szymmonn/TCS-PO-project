package io.github.StoneDigger.model.Interfaces;
import io.github.StoneDigger.model.Classes.LevelStatus;

//ma dostep do klasy iLevelStats
//ma klasy jak end, diamond, playerKiller

public interface IStatsChanger {
    void changeStat(String statName, int delta);
}
