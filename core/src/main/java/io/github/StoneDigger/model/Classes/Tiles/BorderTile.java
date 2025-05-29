package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.model.Interfaces.ITile;

public class BorderTile implements ITile {
    IBoard board;
    public void setBoard(IBoard board) {
        this.board = board;
    }

    @Override public boolean isWalkable() { return false; }

}
