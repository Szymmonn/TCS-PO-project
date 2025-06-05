package io.github.StoneDigger.model.Level.Managers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.IBoard;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;

public class BoardManager {
    private final IBoard board;

    /*
    public BoardManager(char[][] board) {
        // ...
        this.board = null;
    }
     */

    public BoardManager(IBoard board) {
        this.board = board;
    }


    public IBoard getBoard() {
        return board;
    }

    public ATile getTile(GridPoint2 pos) {
        return board.getTile(pos);
    }

    public void setTile(GridPoint2 pos, ATile tile) {
        board.setTile(pos, tile);
    }

    public int getHeight() {
        return board.getHeight();
    }

    public int getWidth() {
        return board.getWidth();
    }

    public void setTiles(ATile [][] tiles) {
        board.setTiles(tiles);
    }
    public ATile[][] getTiles() {
        return board.getTiles();
    }
}
