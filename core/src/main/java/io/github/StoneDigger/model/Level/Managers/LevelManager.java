package io.github.StoneDigger.model.Level.Managers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Boards.BoardGenerators.LevelsLoader;
import io.github.StoneDigger.model.GameLogic.ELevelType;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Interfaces.*;

import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    // === Core Components ===
    private final WhatChanged whatChanged;
    private LevelStats levelStats;
    private UpdateManager updateManager;
    private PlayerManager playerManager;
    private OpponentManager opponentManager;
    private BoardManager boardManager;

    // === State Flags ===
    private boolean isGameLost = false;
    private boolean isGameWon = false;

    private final ELevelType levelType;

    // === Constructor ===
    public LevelManager(WhatChanged whatChanged, ELevelType levelType) {
        this.whatChanged = whatChanged;
        this.levelType = levelType;
        this.levelStats = new LevelStats();
        this.updateManager = new UpdateManager();
    }

    // === Board Conversion ===
    public ATile[][] convertBoard(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0)
            throw new IllegalArgumentException("Board nie może być pusty lub null.");

        int rows = board.length;
        int cols = board[0].length;
        ATile[][] tiles = new ATile[cols][rows];

        for (int y = 0; y < rows; y++) {
            if (board[y] == null || board[y].length != cols)
                throw new IllegalArgumentException("Wszystkie wiersze muszą mieć tę samą długość.");

            for (int x = 0; x < cols; x++) {
                char ch = board[y][x];
                GridPoint2 pos = new GridPoint2(x, y);
                ATile tile;

                switch (ch) {
                    case 'd': tile = new DirtTile(pos, boardManager); break;
                    case 'r': tile = new RockTile(pos, boardManager, updateManager, playerManager, levelStats, whatChanged); break;
                    case 'a': tile = new DiamondTile(pos, boardManager, updateManager, whatChanged); break;
                    case 'c': tile = new BrickTile(pos, boardManager); break;
                    case 's': tile = new StartTile(pos, boardManager); break;
                    case 'e': tile = new EndTile(pos, boardManager, levelStats, this); break;
                    case 'x': tile = new DeactivatedEndTile(pos, boardManager, levelStats, updateManager, this); break;
                    case 'b': tile = new BorderTile(pos, boardManager); break;
                    case 'h': tile = new ShelterTile(pos, boardManager); break;
                    case 'o': case 'p': case '.': tile = new EmptyTile(pos, boardManager); break;
                    default:
                        throw new IllegalArgumentException("Nieznany znak: '" + ch + "' na pozycji (" + y + "," + x + ")");
                }

                tiles[x][y] = tile;
            }
        }

        return tiles;
    }

    // === Level Initialization ===
    public void startNewLevel() {
        levelStats.resetLevelSTats();
        levelStats.incrementLevelNumber();
        GridPoint2 startPosition = new GridPoint2(1, 1);

        char[][] raw = BoardGenerator.generateBoard(levelType, levelStats.getLevelNumber());

        // Pre-board setup
        ATile[][] placeholder = new ATile[raw[0].length][raw.length];
        boardManager = new BoardManager(new Board(placeholder));
        playerManager = new PlayerManager(startPosition, boardManager, levelStats, updateManager, whatChanged);

        // Read opponent positions
        List<GridPoint2> startOpponents = new ArrayList<>();
        int counterO = 0, counterP = 0;
        for (int pass = 0; pass < 2; pass++) {
            for (int y = 0; y < raw.length; y++) {
                for (int x = 0; x < raw[0].length; x++) {
                    char ch = raw[y][x];
                    if (pass == 0 && ch == 'o') {
                        startOpponents.add(new GridPoint2(x, y));
                        counterO++;
                    } else if (pass == 1 && ch == 'p') {
                        startOpponents.add(new GridPoint2(x, y));
                        counterP++;
                    }
                }
            }
        }

        opponentManager = new OpponentManager(boardManager, updateManager, playerManager, levelStats, counterO, counterP);
        boardManager.setTiles(convertBoard(raw));

        // Set up updates
        updateManager.clearAll();
        for (int y = 0; y < raw.length; y++) {
            for (int x = 0; x < raw[0].length; x++) {
                ATile tile = boardManager.getBoard().getTile(new GridPoint2(x, y));
                if (tile instanceof StartTile)
                    startPosition = new GridPoint2(x, y);
                if (tile instanceof ISelfUpdate)
                    updateManager.addToUpdates((ISelfUpdate) tile);
            }
        }

        // Set starting positions
        playerManager.getPlayer().setStartingPosition(startPosition);
        for (int i = 0; i < counterO; i++) {
            IOpponent opp = opponentManager.getOpponents().get(i);
            opp.setStartingPosition(startOpponents.get(i));
            opp.setOnStartingPosition();
        }
        for (int i = 0; i < counterP; i++) {
            IOpponent opp = opponentManager.getOpponents().get(i + counterO);
            opp.setStartingPosition(startOpponents.get(i + counterO));
            opp.setOnStartingPosition();
        }

        // Register components for updates
        updateManager.addPlayerManager(playerManager);
        for (IOpponent opp : opponentManager.getOpponents())
            updateManager.addToUpdates(opp);

        startMechanics(levelStats.getLevelNumber(), boardManager.getBoard());
    }

    // === Game Mechanics Initialization ===
    public void startMechanics(int levelIndex, IBoard board) {
        int diamonds = 0;

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (board.getTile(new GridPoint2(x, y)) instanceof DiamondTile) {
                    diamonds++;
                }
            }
        }

        playerManager.getPlayer().setOnStartingPosition();
        levelStats.setDiamondCount(diamonds);
        levelStats.setLevelNumber(levelIndex);
    }

    // === Game Tick ===
    public void tick(float delta) {
        updateManager.updateAll(delta);
        levelStats.update(delta);

        if (levelStats.getHP() == 0)
            isGameLost = true;

        if (levelType == ELevelType.RANDOM && levelStats.getLevelNumber() == LevelsLoader.levelsToWinRandom() + 1)
            isGameWon = true;
        else if(levelType == ELevelType.STANDARD && levelStats.getLevelNumber() == LevelsLoader.levelToWinStandard() + 1)
            isGameWon = true;

        opponentManager.tryClearOpponents(delta);
    }

    // === External Accessors ===
    public Player getPlayer() {
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

    public boolean isGameLost() {
        return isGameLost;
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public void movePlayer(EDirections direction) {
        playerManager.movePlayer(direction);
    }
}
