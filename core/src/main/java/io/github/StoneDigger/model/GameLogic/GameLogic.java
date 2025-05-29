package io.github.StoneDigger.model.GameLogic;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardValidators.SimpleBoardValidator;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;
import io.github.StoneDigger.model.Level.LevelManagement.UpdateManager;
import io.github.StoneDigger.model.Level.PlayerManager;

import static io.github.StoneDigger.model.Level.LevelManagement.LevelManager.setBoard;



/*
responsible for game cycle
 */
public class GameLogic {
    private static int levelNumber;
    private static boolean gameOver;
    private static final SimpleBoardValidator validator = new SimpleBoardValidator();

    public static void startTheGame() {
        setGameOver(false);
        Board empty = new Board(new ATile[1+20][1+15], new GridPoint2(1,1));
        setBoard(empty);
        LevelManager.startLevel(1, empty);
        Board full;
        //do {
            full = BoardGenerator.generateBoard(1);
        //} while (!validator.validate(full));
        LevelManager.startLevel(1,full);
        LevelManager.setBoard(full);
        PlayerManager.setPlayer(new Player(new GridPoint2(1,1)));
        levelNumber = 0;
        startNewLevel();
    }

    public static void startNewLevel() {
        levelNumber++;
        //LevelManager.startLevel(1, Level[levelNumber]);
    }

    public static void setGameOver(boolean b) {
        gameOver = b;
    }

    public static void tick(float delta) {
        UpdateManager.updateAll(delta);
    }

    public static boolean isGameOver() {
        return gameOver;
    }
}
