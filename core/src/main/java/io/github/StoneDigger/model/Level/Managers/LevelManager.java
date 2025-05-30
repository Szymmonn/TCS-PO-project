package io.github.StoneDigger.model.Level.Managers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Boards.BoardGenerators.ELevelType;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.GameObjects.Tiles.DiamondTile;
import io.github.StoneDigger.model.Level.LevelStats;

public abstract class LevelManager {
    private static Board board = null;
    private static LevelStats stats = null;
    private static int levelNumber;
    private static boolean gameOver;

    public static void startNewLevel() {
        levelNumber++;
        Board newBoard = BoardGenerator.generateBoard(ELevelType.STANDARD, levelNumber);
        setBoard(newBoard);

        UpdateManager.clearAll();              // <-- MUSI BYĆ: usuwa wszystkie stare wpisy
        UpdateManager.addToUpdates(PlayerManager.getPlayer());

        for (int x = 0; x < newBoard.getWidth(); x++) {
            for (int y = 0; y < newBoard.getHeight(); y++) {
                ATile t = newBoard.getTile(new GridPoint2(x, y));
                if (t instanceof ISelfUpdate) {
                    UpdateManager.addToUpdates((ISelfUpdate) t);
                }
            }
        }

//        // (Twoje debugujące printlny można zostawić albo usunąć)
        startMechanics(levelNumber, newBoard);
//
//
//
//
//        levelNumber++;
//        setBoard(BoardGenerator.generateBoard(ELevelType.STANDARD, levelNumber));
//        System.out.println("cos" + LevelManager.getBoard().getTile(new GridPoint2(6, 1)));
//        for(int i = 0; i < LevelManager.getBoard().getWidth(); i++) {
//            for(int j = 0; j < LevelManager.getBoard().getHeight(); j++) {
//                System.out.println("cos" + LevelManager.getBoard().getTile(new GridPoint2(i, j)));
//            }
//            System.out.println('\n');
//        }
//        LevelManager.startMechanics(levelNumber, LevelManager.getBoard());

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
