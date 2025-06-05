package io.github.StoneDigger.model.Level.Managers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Boards.BoardGenerators.ELevelType;
import io.github.StoneDigger.model.Boards.BoardGenerators.Levels;
import io.github.StoneDigger.model.Boards.IBoard;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.IPlayer;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.LevelStats;

public class LevelManager {
    private ILevelStats levelStats;
    private UpdateManager updateManager;
    private PlayerManager playerManager;
    private BoardManager boardManager;

    public LevelManager() {
        levelStats = new LevelStats();
        updateManager = new UpdateManager();
    }

    public ATile[][] convertBoard(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            throw new IllegalArgumentException("Board nie może być pusty lub null.");
        }

        int rows = board.length;
        int cols = board[0].length;
        ATile[][] tiles = new ATile[cols][rows];

        for (int y = 0; y < rows; y++) {
            if (board[y] == null || board[y].length != cols) {
                throw new IllegalArgumentException("Wszystkie wiersze muszą mieć tę samą długość.");
            }
            for (int x = 0; x < cols; x++) {
                char ch = board[y][x];
                GridPoint2 pos = new GridPoint2(x, y);
                ATile tile;
                switch (ch) {
                    case 'd': tile = new DirtTile(pos, boardManager); break;
                    case 'r': tile = new RockTile(pos, boardManager, updateManager, playerManager); break;
                    case 'a': tile = new DiamondTile(pos, boardManager, updateManager); break;
                    case ' ': tile = new EmptyTile(pos, boardManager); break;
                    case 'c': tile = new BrickTile(pos, boardManager); break;
                    case 's': tile = new StartTile(pos, boardManager); break;
                    case 'e': tile = new EndTile(pos, boardManager, levelStats, this); break;
                    case 'x': tile = new DeactivatedEndTile(pos, boardManager, levelStats, this); break;  // should use only this end tile
                    case 'b': tile = new BorderTile(pos, boardManager); break;
                    default:
                        throw new IllegalArgumentException(
                            "Nieznany znak: '" + ch + "' na pozycji (" + y + "," + x + ")"
                        );
                }
                tiles[x][y] = tile;
            }
        }

        return tiles;
    }

    public void startNewLevel(ELevelType levelType) {
        levelStats.resetLevelSTats();
        levelStats.incrementLevelNumber();
        GridPoint2 startPosition = new GridPoint2(1, 1);

        char[][] raw = BoardGenerator.generateBoard(levelType, levelStats.getLevelNumber());

        ATile[][] placeholder = new ATile[raw[0].length][raw.length];
        Board tempBoard = new Board(placeholder);

        boardManager = new BoardManager(tempBoard);

        playerManager = new PlayerManager(startPosition, boardManager, levelStats, updateManager);

        ATile[][] tiles = convertBoard(raw);

        boardManager.setTiles(tiles);

        updateManager.clearAll();
        System.out.println("gamelogic po starcie");

        int h = raw.length, w = raw[0].length;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                ATile t = boardManager.getBoard().getTile(new GridPoint2(x, y));
                if (t instanceof StartTile) {
                    startPosition = new GridPoint2(x, y);
                }
                if (t instanceof ISelfUpdate) {
                    updateManager.addToUpdates((ISelfUpdate) t);
                }
            }
        }

        playerManager.getPlayer().setStartingPosition(startPosition);

        System.out.println("levelmanager po przjesciach");

        updateManager.addToUpdates(playerManager.getPlayer());

        startMechanics(levelStats.getLevelNumber(), boardManager.getBoard());
    }


    public void startMechanics(int index, IBoard board) {
        int count = 0;
        int width = board.getWidth();
        int height = board.getHeight();

        for (int n = 0; n < width * height; n++) {
            if (board.getTile(new GridPoint2(n % width, n / width)) instanceof DiamondTile) {
                count++;
            }
        }

        playerManager.getPlayer().setOnStartingPosition();
        levelStats.setHP(3);
        levelStats.setDiamondCount(count);
        levelStats.setLevelNumber(index);
    }

    public void tick(float delta) {
        updateManager.updateAll(delta);
    }

    public IPlayer getPlayer() {
        return playerManager.getPlayer();
    }
    public ILevelStats getLevelStats() {
        return levelStats;
    }
    public IBoard getBoard() {
        return boardManager.getBoard();
    }

    public void movePlayer(EDirections direction) {
        playerManager.movePlayer(direction);
    }
}
