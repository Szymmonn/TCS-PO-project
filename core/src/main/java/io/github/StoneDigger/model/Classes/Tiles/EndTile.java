package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.*;

public class EndTile implements ITile, IWalkableTile {
    IBoard board;

    @Override public boolean isWalkable() { return true; }

    public void setBoard(IBoard board) {
        this.board = board;
    }

    @Override
    public void onWalkBy(IEntity entity) {

    }
}
