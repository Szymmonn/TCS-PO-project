package io.github.StoneDigger.BoardGenerators;


public interface IBoardGenerator {
    Board generate(int width, int height, int startingPositionX, int startingPositionY);
}

