package io.github.StoneDigger.model1.BoardGenerators;


import io.github.StoneDigger.model1.models.BoardModel;

public interface IBoardGenerator {
    BoardModel generate(int width, int height, int startingPositionX, int startingPositionY);
}

