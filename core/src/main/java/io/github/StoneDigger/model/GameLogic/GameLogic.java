package io.github.StoneDigger.model.GameLogic;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardValidators.SimpleBoardValidator;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;

import static io.github.StoneDigger.model.Level.LevelManagement.LevelManager.setBoard;

public class GameLogic {
    private static boolean gameOver;

    /// TODO: its the same board as in levelmanager
    private static Board board;
    private static final SimpleBoardValidator validator = new SimpleBoardValidator();

    public static void init() {
        setGameOver(false);
        Board empty = new Board(new ATile[1+20][1+15], new GridPoint2(1,1));
        setBoard(empty);
        LevelManager.startLevel(1, empty);
        Board full;
        //do {
            full = BoardGenerator.generateBoard(1);
        //} while (!validator.validate(full));
        LevelManager.startLevel(1,full); gameOver = false;
        board = full;
        LevelManager.setPlayer(new Player(new GridPoint2(1,1)));
        System.out.println("Init done.");
        System.out.println("Board width: " + board.getWidth());
        System.out.println("Player pos: " + LevelManager.getPlayer().getPosition());

    }

    public static void setGameOver(boolean b) {
        gameOver = b;
    }

    public static void tick(float delta) {
        LevelManager.update(delta);
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static Board getBoard() {
        return board;
    }
}
