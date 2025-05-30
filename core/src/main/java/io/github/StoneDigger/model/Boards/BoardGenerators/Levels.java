package io.github.StoneDigger.model.Boards.BoardGenerators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Level.Managers.LevelManager;

import javax.swing.border.Border;

public class Levels {
    public static GridPoint2[] startingPoints = {
        new GridPoint2(1, 1),
        new GridPoint2(1, 1),
        new GridPoint2(1, 1),
        new GridPoint2(1, 1),
        new GridPoint2(1, 1)
    };

    public static char[][][] boards = {
        {
            "s   r d c  r  a    d".toCharArray(),
            "d ccrrda ddr rc ce d".toCharArray(),
            "d d  d r c  d  ra  d".toCharArray(),
            "d rr c a rr dd rc  d".toCharArray(),
            "d a  rr  dd rc  d  d".toCharArray(),
            "d dd c d cc  a  r  d".toCharArray(),
            "d c  dd ra  r  c d d".toCharArray(),
            "d r dd  d c dd  rc d".toCharArray(),
            "d  r   c r a  d  r d".toCharArray(),
            "dd     d  r     a  e".toCharArray()
        },
        {
            "s    a   r   c   d  d  a ".toCharArray(),
            "d rc r ddr cc rr  a d  d ".toCharArray(),
            "d d  rc a  dd  r c  r cd ".toCharArray(),
            "d d  r  c  a  rc dd r dd ".toCharArray(),
            "d cc a  d  rc  d  a  c d ".toCharArray(),
            "d dd r  dd r  d  r dd  d ".toCharArray(),
            "d r  dd rc   c  a r  d d ".toCharArray(),
            "d a cc r   ddr  c d rc d ".toCharArray(),
            "d  dd a  r   c dd  a dd d".toCharArray(),
            "d r   d  a  rc  d  rc  d".toCharArray(),
            "dd  d    r  a    r  d   d".toCharArray(),
            "d   d  rc d d  r   a  c e".toCharArray()
        },
        {
            "s d  c   a  r   dd rc  a   d   ".toCharArray(),
            "d rc ddr  c a dd r   d r c r d ".toCharArray(),
            "d a  r  cc  d  a  d rc   d  r d ".toCharArray(),
            "d dd  d  r  dd  cc r  a  dd c d ".toCharArray(),
            "d  r  c  a  r  d  r  c d  ra  d ".toCharArray(),
            "d dd a dd  c  r dd  a  d  d  dd ".toCharArray(),
            "d r   d   d  a rc   d  cc  r  d ".toCharArray(),
            "d  a c rc d  d r   a dd   r  cd ".toCharArray(),
            "d c  d d   a  rc d  r  a  d r d ".toCharArray(),
            "d dd  r d c  a   dd c r dd  d d ".toCharArray(),
            "d   r  a dd  d  r  c d  a   r d ".toCharArray(),
            "d  c d   r dd  a  dd r  c  d  d ".toCharArray(),
            "dd  a  c r   d  r   a  d  r   d ".toCharArray(),
            "d    d   d  c a  d   rc dd  dde".toCharArray()
        },
        {
            "s d  r   c  dd   r   a d  c dd      ".toCharArray(),
            "d cc a r  d  a c  d  r  dd r a   c d ".toCharArray(),
            "d dd  rc d r  d cc a dd  r  d rc  d ".toCharArray(),
            "d r  dd a  c  r  d  a  rc  d  a  d d ".toCharArray(),
            "d a   r  dd   a  dd r   d  c  r  dd  ".toCharArray(),
            "d dd  d  rc a d  r  dd  a r   c  dd  ".toCharArray(),
            "d   r a   d  r  cc d  a  r dd   a d ".toCharArray(),
            "d d  dd  r c  a   r  d  rc d  a  cd ".toCharArray(),
            "d rc   d  r dd  a   c r  d a   dd d ".toCharArray(),
            "d dd a  c d   r  dd  a  c  dd  r  d ".toCharArray(),
            "d  c r  dd  a  r  d rc   d  a  r  d ".toCharArray(),
            "d  a  d r   d  cc  r  d  a   dd c d ".toCharArray(),
            "d d   a r  dd   d a  rc d  r  dd  d ".toCharArray(),
            "d  r c d a   rc  d  r  dd  c  a   d ".toCharArray(),
            "dd   d   r  a   d   r  a   r   d  d ".toCharArray(),
            "d a   c d   d a  d   rc d  a  c   de".toCharArray()
        },
        {
            "s  a  rc  d  a dd r  a  c d  a dd  r    d".toCharArray(),
            "d rc d  r   d  rc a  dd   r  c a  d  r  d".toCharArray(),
            "d  dd  a   rc d  a  r  dd  a  r dd   a  d".toCharArray(),
            "d c  r dd a  d   r  c  dd a  r   c d  a d".toCharArray(),
            "d dd  a   r  a dd r a  d  rc d  a  r dd d".toCharArray(),
            "d  r  c  d  rc   d  a   r dd  a  rc d  d ".toCharArray(),
            "d a  d r a   dd  a  r   d  a dd  r  a  d ".toCharArray(),
            "d dd  a  rc  d  a  dd  r  c  a   r dd  d ".toCharArray(),
            "d   r  dd  a  r dd  a  d  rc  dd  a   d ".toCharArray(),
            "d  a  r  c  a   d   rc d   d  a r  dd d ".toCharArray(),
            "d dd  a  d  r dd  a  r   c  a   d  r  d ".toCharArray(),
            "d  c r  d a  rc d  a  dd  r  a dd  r  d ".toCharArray(),
            "d   a  rc d  a dd r  a  d   r dd  a   d ".toCharArray(),
            "d  r dd  a   r  a  c  dd  a  r   d  rc d".toCharArray(),
            "d dd  a   d rc  d  a  r dd  a   r   d d".toCharArray(),
            "d  r  c a   dd  r a  d  rc   a  d r  d ".toCharArray(),
            "d a   d   r  a dd  r   d  a dd   r  d d".toCharArray(),
            "d dd  a   r dd  a  rc  d  a  r dd  a  d".toCharArray(),
            "d   rc  d  a  r dd  a   rc  d  a  dd  d".toCharArray(),
            "dd  a   dd  r   d  a  dd  r a  d  r   d".toCharArray(),
            "d a  rc  d  a r dd   a  r  d  rc   a d".toCharArray(),
            "d  dd  a   r dd a   r   d  a dd  r  a e".toCharArray()
        }
    };


    public static ATile[][] convertBoard(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            throw new IllegalArgumentException("Board nie może być pusty lub null.");
        }

        int rows = board.length;
        int cols = board[0].length;
        ATile[][] tiles = new ATile[cols][rows];

        for (int y = 0; y < rows; y++) {
            if (board[y] == null || board[y].length != cols) {
                throw new IllegalArgumentException("Wszystkie wiersze muszą mieć tę samą długość.");
            }
            for (int x = 0; x < cols; x++) {
                char ch = board[y][x];
                GridPoint2 pos = new GridPoint2(x, y);
                ATile tile;
                switch (ch) {
                    case 'd': tile = new DirtTile(pos); break;
                    case 'r': tile = new RockTile(pos); break;
                    case 'a': tile = new DiamondTile(pos); break;
                    case ' ': tile = new EmptyTile(pos); break;
                    case 'c': tile = new BrickTile(pos); break;
                    case 's': tile = new StartTile(pos); break;
                    case 'e': tile = new EndTile(pos); break;
                    case 'x': tile = new DeactivatedEndTile(pos); break;
                    case 'b': tile = new BorderTile(pos); break;
                    default:
                        throw new IllegalArgumentException(
                            "Nieznany znak: '" + ch + "' na pozycji (" + y + "," + x + ")"
                        );
                }
                tiles[x][y] = tile;
            }
        }

        return tiles;
    }


    public static char[][][] normalizeAndFrameBoards(char[][][] rawBoards) {
        if (rawBoards == null) throw new IllegalArgumentException("Brak plansz.");

        char[][][] result = new char[rawBoards.length][][];

        for (int i = 0; i < rawBoards.length; i++) {
            char[][] board = rawBoards[i];

            int maxWidth = 0;
            for (char[] row : board) {
                if (row != null) maxWidth = Math.max(maxWidth, row.length);
            }

            int newHeight = board.length + 2;
            int newWidth = maxWidth + 2;
            char[][] framed = new char[newHeight][newWidth];

            for (int y = 0; y < newHeight; y++) {
                for (int x = 0; x < newWidth; x++) {
                    if (y == 0 || y == newHeight - 1 || x == 0 || x == newWidth - 1) {
                        framed[y][x] = 'b';
                    } else {
                        char[] originalRow = board[y - 1];
                        framed[y][x] = originalRow != null && x - 1 < originalRow.length
                            ? originalRow[x - 1] : ' ';
                    }
                }
            }

            result[i] = framed;
        }

        return result;
    }

}
