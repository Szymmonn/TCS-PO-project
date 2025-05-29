package io.github.StoneDigger.model.GameLogic;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardValidators.SimpleBoardValidator;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;

public class GameLogic {
    private static boolean gameOver;
    private static Board board;
    private static Player player;
    private final SimpleBoardValidator validator;

    public GameLogic() {
        this.validator = new SimpleBoardValidator();
        init();
    }

    public static void init() {
        Board empty = new Board(new ATile[1+20][1+15], new GridPoint2(1,1));
        LevelManager.startLevel(1, empty);
        Board full;
        //do {
            full = BoardGenerator.generateBoard(1);
        //} while (!validator.validate(full));
        LevelManager.startLevel(1,board); gameOver = false;
        board = full;
        player = new Player(board);
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

    public static Player getPlayer() {
        return player;
    }
}
