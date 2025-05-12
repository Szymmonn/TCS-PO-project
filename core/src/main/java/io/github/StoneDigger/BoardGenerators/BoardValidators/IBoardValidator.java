package io.github.StoneDigger.BoardGenerators.BoardValidators;

import io.github.StoneDigger.models.BoardModel;

public interface IBoardValidator {
    boolean validate(BoardModel board);
}
