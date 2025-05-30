package io.github.StoneDigger.model.Level.Managers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.GameObjects.Tiles.DiamondTile;
import io.github.StoneDigger.model.Level.LevelStats;

public abstract class LevelManager {
    private static Board board = null;
    private static LevelStats stats = null;

    public static void resetLevel() {
        startLevel(2, board);
    }

    public static void startLevel(int index, Board board) {

        LevelManager.board = board;
        /*
        all diamonds on board counter
         */
        int count = 0;
        int width = board.getWidth();
        int height = board.getHeight();

        // TO CHANGE
        GridPoint2 playerStartPosition = new GridPoint2(1,1);

        for(int n=0;n<width * height; n++) {
            if (board.getTile(new GridPoint2(n % width, n / width)) instanceof DiamondTile) {
                count++;
            }
        }
        PlayerManager.getPlayer().setPosition(playerStartPosition);
        stats = new LevelStats(count,3, index);
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
