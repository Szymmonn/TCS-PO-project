package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.ITile;

public class EmptyTile implements ITile {
    IBoard board;

    public void setBoard(IBoard board) {
        this.board = board;
    }

    @Override public boolean isWalkable() { return true; }


    @Override
    public void onWalkBy(IEntity entity) {

    }
}
