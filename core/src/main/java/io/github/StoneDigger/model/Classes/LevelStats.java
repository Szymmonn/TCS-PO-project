package io.github.StoneDigger.model.Classes;

import io.github.StoneDigger.model.Interfaces.ILevelStats;

public class LevelStats implements ILevelStats {
    private int score;
    @Override public int getScore() { return score; }
    @Override public void addScore(int delta) { score += delta; }
    @Override public boolean isLevelComplete() {
        // Check if player has reached the exit position
        // TODO: inject access to player and exit coordinates
        return false;
    }
}
