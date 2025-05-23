package io.github.StoneDigger.model.BoardGenerators.BoardValidators;

import io.github.StoneDigger.model.models.BoardModel;

public interface IBoardValidator {
    boolean validate(BoardModel board);
}
