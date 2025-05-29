package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.*;

public class StartTile implements ITile {
    IBoard board;

    public void setBoard(IBoard board) {
        this.board = board;
    }

    @Override public boolean isWalkable() { return true; }

    @Override
    public void onWalkBy(IEntity entity) {
        return;
    }
}
