package io.github.StoneDigger.BoardGenerators.BoardValidators;

import io.github.StoneDigger.BoardGenerators.Board;

public interface IBoardValidator {
    boolean validate(Board board, int startingPositionX, int startingPositionY);
}
