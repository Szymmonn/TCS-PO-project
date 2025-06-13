package io.github.StoneDigger.model.Level.Managers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Boards.BoardGenerators.Levels;
import io.github.StoneDigger.model.GameLogic.ELevelType;
import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Interfaces.IOpponent;
import io.github.StoneDigger.model.Interfaces.IPlayer;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;
import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Level.LevelStats;

import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    private final WhatChanged whatChanged;
    private LevelStats levelStats;
    private UpdateManager updateManager;
    private PlayerManager playerManager;
    private OpponentManager opponentManager;
    private BoardManager boardManager;
    private boolean isGameLost;
    private boolean isGameWon;

    private ELevelType levelType;

    public LevelManager(WhatChanged whatChanged, ELevelType levelType) {
        levelStats = new LevelStats();
        updateManager = new UpdateManager();
        this.whatChanged = whatChanged;

        isGameLost = false;
        isGameWon = false;

        this.levelType = levelType;
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
                    case 'r': tile = new RockTile(pos, boardManager, updateManager, playerManager, levelStats,whatChanged); break;
                    case 'a': tile = new DiamondTile(pos, boardManager, updateManager,whatChanged); break;
                    case ' ': tile = new EmptyTile(pos, boardManager); break;
                    case 'c': tile = new BrickTile(pos, boardManager); break;
                    case 's': tile = new StartTile(pos, boardManager); break;
                    case 'e': tile = new EndTile(pos, boardManager, levelStats, this); break;
                    case 'x': tile = new DeactivatedEndTile(pos, boardManager, levelStats, this); break;  // should use only this end tile
                    case 'b': tile = new BorderTile(pos, boardManager); break;
                    case 'h': tile = new ShelterTile(pos,boardManager); break;
                    case 'o': tile = new EmptyTile(pos,boardManager);break;
                    case 'p': tile = new EmptyTile(pos,boardManager);break;
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

    public void startNewLevel() {
        levelStats.resetLevelSTats();
        levelStats.incrementLevelNumber();
        GridPoint2 startPosition = new GridPoint2(1, 1);

        char[][] raw;
        if(levelType == ELevelType.RANDOM) {
            raw = BoardGenerator.generateBoard(ELevelType.RANDOM, levelStats.getLevelNumber());
        } else {
            raw = Levels.boards[levelStats.getLevelNumber() +1];
        }


        ATile[][] placeholder = new ATile[raw[0].length][raw.length];
        Board tempBoard = new Board(placeholder);

        boardManager = new BoardManager(tempBoard);
        playerManager = new PlayerManager(startPosition, boardManager, levelStats, updateManager, whatChanged);

        List<GridPoint2> startOpponents = new ArrayList<>();

        int rows = raw.length;
        int cols = raw[0].length;
        int counterO=0,counterP=0;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                char ch = raw[y][x];
                System.out.print(ch);
                if(ch == 'o') {
                    counterO++;
                    startOpponents.add(new GridPoint2(x,y));
                }
            }
        }
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                char ch = raw[y][x];
                System.out.print(ch);
                if(ch == 'p') {
                    counterP++;
                    startOpponents.add(new GridPoint2(x,y));
                }
            }
            System.out.println();
        }

        opponentManager = new OpponentManager(boardManager, updateManager,playerManager,levelStats,counterO,counterP);


        ///  Setting board

        ATile[][] tiles = convertBoard(raw);

        boardManager.setTiles(tiles);

        updateManager.clearAll();

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
        System.out.println("o : "+counterO+"   p: " + counterP+"\n"+startOpponents);
        /// Updating player and opponents to its starting locations

        playerManager.getPlayer().setStartingPosition(startPosition);
        for(int i=0;i<counterO;i++) {
            opponentManager.getOpponents().get(i).setStartingPosition(startOpponents.get(i));
            opponentManager.getOpponents().get(i).setOnStartingPosition();
        }
        for(int i=counterO;i<counterO+counterP;i++) {
            opponentManager.getOpponents().get(i).setStartingPosition(startOpponents.get(i));
            opponentManager.getOpponents().get(i).setOnStartingPosition();
        }

        /// Adding to update Manager

        updateManager.addPlayerManager(playerManager);

        for (IOpponent opp : opponentManager.getOpponents())
            updateManager.addToUpdates(opp);

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
        levelStats.update(delta);
        if(levelStats.getHP() == 0) {
            isGameLost = true;
        }

        if(levelStats.getLevelNumber() == 6) {
            isGameWon = true;
        }
        opponentManager.tryClearOpponents(delta);
    }

    public IPlayer getPlayer() {
        return playerManager.getPlayer();
    }
    public List<IOpponent> getOpponents() {
        return opponentManager.getOpponents();
    }
    public LevelStats getLevelStats() {
        return levelStats;
    }
    public IBoard getBoard() {
        return boardManager.getBoard();
    }

    public void movePlayer(EDirections direction) {
        playerManager.movePlayer(direction);
    }
    public boolean isGameLost() {
        return isGameLost;
    }
    public boolean isGameWon() {
        return isGameWon;
    }
}
