package io.github.StoneDigger.model.Boards.BoardGenerators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Level.Managers.LevelManager;

public class StandardBoardGenerator {

    public static Board generate(int levelNumber) {
        GridPoint2 start = Levels.startingPoints[levelNumber];
        char[][] board = Levels.boards[levelNumber];
        return new Board(Levels.convertBoard(board), start);
    }
}
