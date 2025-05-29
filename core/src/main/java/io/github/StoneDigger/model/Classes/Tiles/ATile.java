package io.github.StoneDigger.model.Classes.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.model.Interfaces.ITile;

public abstract class ATile {
    protected IBoard board;
    protected GridPoint2 position;
    protected void setBoard(IBoard newBoard) {
        board = newBoard;
    }
    protected GridPoint2 getPosition() { return position; }
    protected IBoard getBoard() { return board; }
    protected abstract boolean isWalkable();
}
