package io.github.StoneDigger.BoardGenerators;


public interface IBoardGenerator {
    TileType[][] generate(int width, int height, int startingPositionX, int startingPositionY);
}

