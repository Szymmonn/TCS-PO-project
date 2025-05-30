package io.github.StoneDigger.model.Level.Managers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Boards.BoardGenerators.ELevelType;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.GameObjects.Tiles.DiamondTile;
import io.github.StoneDigger.model.Level.LevelStats;

public abstract class LevelManager {
    private static Board board = null;
    private static LevelStats stats = null;
    private static int levelNumber;
    private static boolean gameOver;

    public static void resetLevel() {
        startMechanics(2, board);
    }

    public static void startNewLevel() {
        levelNumber++;
        setBoard(BoardGenerator.generateBoard(ELevelType.STANDARD, levelNumber));
        LevelManager.startMechanics(levelNumber, LevelManager.getBoard());
    }

    public static void startMechanics(int index, Board board) {
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

    public static boolean isGameOver() {
        return LevelManager.gameOver;
    }

    public static void setGameOver(boolean b) {
        LevelManager.gameOver = b;
    }

    public static void setLevelNumber(int levelNumber) {
        LevelManager.levelNumber = levelNumber;
    }
}
