package io.github.StoneDigger.model.Classes.Tiles;

import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.ITile;
import io.github.StoneDigger.model.Interfaces.IWalkableTile;

public class DirtTile implements ITile, IWalkableTile {
    IBoard board;
    public void setBoard(IBoard board) {
        this.board = board;
    }

    @Override public boolean isWalkable() { return true; }


    @Override
    public void onWalkBy(IEntity entity) {
        this.destroy();
    }

    private void destroy() {
    }
}
