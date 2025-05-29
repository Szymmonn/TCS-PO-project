package io.github.StoneDigger.model.Interfaces;
import io.github.StoneDigger.model.Classes.LevelStatus;

public interface IStatsChanger {
    void changeStat(String statName, int delta);
}
