package io.github.StoneDigger.model.Level.Managers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Boards.BoardGenerators.ELevelType;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.GameObjects.Tiles.DiamondTile;
import io.github.StoneDigger.model.GameObjects.Tiles.StartTile;
import io.github.StoneDigger.model.Level.LevelStats;

public abstract class LevelManager {
    private static Board board = null;
    private static LevelStats stats = new LevelStats(0,3, 0);;
    private static int levelNumber=-1;
    private static boolean gameOver;
    private static GridPoint2 startPosition = new GridPoint2();

    public static void startNewLevel() {
        levelNumber++;
        Board newBoard = BoardGenerator.generateBoard(ELevelType.STANDARD, levelNumber);
        setBoard(newBoard);

        UpdateManager.clearAll();
        System.out.println("gamelogic po starcie");

        for (int x = 0; x < newBoard.getWidth(); x++) {
            for (int y = 0; y < newBoard.getHeight(); y++) {
                ATile t = newBoard.getTile(new GridPoint2(x, y));
                if(t instanceof StartTile) startPosition = new GridPoint2(x,y);
                if (t instanceof ISelfUpdate) {
                    UpdateManager.addToUpdates((ISelfUpdate) t);
                }
            }
        }

        System.out.println("levelmanager po przjesci");

        PlayerManager.setPlayer(new Player(startPosition));
        UpdateManager.addToUpdates(PlayerManager.getPlayer());
        startMechanics(levelNumber, newBoard);

    }

    public static void startMechanics(int index, Board board) {
        /*
        all diamonds on board counter
         */
        int count = 0;
        int width = board.getWidth();
        int height = board.getHeight();


        for(int n=0;n<width * height; n++) {
            if (board.getTile(new GridPoint2(n % width, n / width)) instanceof DiamondTile) {
                count++;
            }
        }
        PlayerManager.getPlayer().setPosition(startPosition);
        stats.setHP(3);
        stats.setDiamondCount(count);
        stats.setLevelNumber(index);

    }

    public static LevelStats getStats() {
        return stats;
    }

    public static void setBoard(Board board) {
        if(levelNumber!=-1) LevelManager.board.setTiles(board.getTiles());
        else LevelManager.board = board;
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
