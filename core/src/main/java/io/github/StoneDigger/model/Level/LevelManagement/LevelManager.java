package io.github.StoneDigger.model.Level.LevelManagement;

import com.badlogic.gdx.math.GridPoint2;
import java.util.List;
import java.util.ArrayList;

import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameObjects.Entities.IEntity;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;

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
