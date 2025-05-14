package io.github.StoneDigger.BoardGenerators;


import io.github.StoneDigger.models.BoardModel;

public interface IBoardGenerator {
    BoardModel generate(int width, int height, int startingPositionX, int startingPositionY);
}

