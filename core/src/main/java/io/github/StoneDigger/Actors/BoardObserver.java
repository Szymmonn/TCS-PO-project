package io.github.StoneDigger.Actors;

import io.github.StoneDigger.BoardGenerators.Board;
import io.github.StoneDigger.BoardGenerators.TileType;

public interface BoardObserver {
    void onTileChanged(int x, int y, TileType previousType, Board board);
}
