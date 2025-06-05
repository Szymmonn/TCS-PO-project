package io.github.StoneDigger.model.Boards.BoardGenerators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Level.Managers.LevelManager;

public class StandardBoardGenerator implements IBoardGenerator {

    public static char[][] generate(int levelNumber) {
        return Levels.boards[levelNumber];
    }
}
