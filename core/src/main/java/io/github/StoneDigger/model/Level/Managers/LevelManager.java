package io.github.StoneDigger.model.Level.Managers;

import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Level.LevelStats;

public abstract class LevelManager {
    private static Board board = null;
    private static LevelStats stats = null;

    public static void resetLevel() {
        startLevel(1, board);
    }

    public static void startLevel(int index, Board bboard) {
        board = bboard;
        stats = new LevelStats(0,0, index);
    }

    public static LevelStats getStats() {
        return stats;
    }

    public static void setBoard(Board board) {
        LevelManager.board = board;
    }

    public static Board getBoard() {
        return board;
    }
}
