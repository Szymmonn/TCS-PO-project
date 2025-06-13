package io.github.StoneDigger.model.Boards.BoardGenerators;

import io.github.StoneDigger.model.Interfaces.IBoardGenerator;

public class StandardBoardGenerator implements IBoardGenerator {

    public static char[][] generate(int levelNumber) {
        return Levels.boards[levelNumber % 5]; //TODO: make it normal
    }
}
