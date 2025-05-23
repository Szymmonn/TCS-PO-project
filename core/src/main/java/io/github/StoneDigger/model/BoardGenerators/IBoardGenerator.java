package io.github.StoneDigger.model.BoardGenerators;


import io.github.StoneDigger.model.models.BoardModel;

public interface IBoardGenerator {
    BoardModel generate(int width, int height, int startingPositionX, int startingPositionY);
}

