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

    private static final char[][][] rawBoards = {
        {
            "sdddrdddcddrddaddddd".toCharArray(),
            "ddccrrdadddrdrcdcddd".toCharArray(),
            "dddddddrdcdddddraddd".toCharArray(),
            "ddrrdcdadrrddddrcddd".toCharArray(),
            "ddaddrrdddddrcdddddd".toCharArray(),
            "dddddcdddccddaddrddd".toCharArray(),
            "ddcdddddraddrddcdddd".toCharArray(),
            "ddrdddddddcdddddrcdd".toCharArray(),
            "dddrdddcdrdadddddrdd".toCharArray(),
            "ddddddddddrdddddadde".toCharArray()
        },

        {
            "sddddadddrdddcddddddddda".toCharArray(),
            "ddrcdrdddrdccdrrddaddddd".toCharArray(),
            "dddddrcdaddddddrdcddrdcd".toCharArray(),
            "dddddrddcddaddrcddddrddd".toCharArray(),
            "ddccdadddddrcdddddaddcdd".toCharArray(),
            "dddddrdddddrdddddrdddddd".toCharArray(),
            "ddrdddddrcdddcddadrddddd".toCharArray(),
            "ddadccdrdddddrddcdddrcdd".toCharArray(),
            "ddddddaddrddcdddddaddddd".toCharArray(),
            "ddrddddddaddrcdddddrcddd".toCharArray(),
            "dddddddddrddadddrddddddd".toCharArray(),
            "dddddddrcddddddrddaddcde".toCharArray()
        },

        {
            "sddddcdddaddrddddddrcddaddddddd".toCharArray(),
            "ddrcdddrddcdaddddrdddddrdcdrddd".toCharArray(),
            "ddaddrddccdddddaddddrcddddddrdd".toCharArray(),
            "dddddddddrddddddccdrddadddddcdd".toCharArray(),
            "dddrddcddaddrdddddrddcddddraddd".toCharArray(),
            "dddddadddddcddrdddddadddddddddd".toCharArray(),
            "ddrddddddddddadrcddddddccddrddd".toCharArray(),
            "dddadcdrcddddddrdddaddddddrddcd".toCharArray(),
            "ddcddddddddaddrcddddrddaddddrdd".toCharArray(),
            "ddddddrdddcddaddddddcdrdddddddd".toCharArray(),
            "ddddrddaddddddddrddcddddadddrdd".toCharArray(),
            "dddcdddddrdddddadddddrddcdddddd".toCharArray(),
            "ddddaddcdrddddddrdddadddddrdddd".toCharArray(),
            "ddddddddddddcdaddddddrcddddddde".toCharArray()
        },

        {
            "sddddrdddcdddddddrdddaddddcddddddddd".toCharArray(),
            "ddccdadrdddddadcdddddrdddddrdadddcdd".toCharArray(),
            "ddddddrcdddrddddccdadddddrddddrcdddd".toCharArray(),
            "ddrdddddaddcddrdddddaddrcdddddaddddd".toCharArray(),
            "ddadddrdddddddadddddrddddddcddrddddd".toCharArray(),
            "dddddddddrcdaddddrddddddadrdddcddddd".toCharArray(),
            "ddddrdaddddddrddccddddaddrddddddaddd".toCharArray(),
            "dddddddddrdcddadddrdddddrcddddaddcdd".toCharArray(),
            "ddrcddddddrdddddadddcdrddddadddddddd".toCharArray(),
            "dddddaddcdddddrddddddaddcddddddrdddd".toCharArray(),
            "dddcdrddddddaddrddddrcddddddaddrdddd".toCharArray(),
            "dddaddddrddddddccddrdddddaddddddcddd".toCharArray(),
            "ddddddadrdddddddddaddrcddddrdddddddd".toCharArray(),
            "dddrdcdddadddrcdddddrddddddcddaddddd".toCharArray(),
            "dddddddddrddadddddddrddadddrdddddddd".toCharArray(),
            "ddadddcdddddddaddddddrcddddaddcdddde".toCharArray()
        },

        {
            "sddaddrcdddddaddddrddaddcddddadddddrddddd".toCharArray(),
            "ddrcddddrddddddrcdadddddddrddcdadddddrddd".toCharArray(),
            "dddddddadddrcddddaddrddddddaddrddddddaddd".toCharArray(),
            "ddcddrddddaddddddrddcdddddaddrdddcddddadd".toCharArray(),
            "ddddddadddrddaddddrdadddddrcddddaddrddddd".toCharArray(),
            "dddrddcdddddrcddddddadddrdddddaddrcdddddd".toCharArray(),
            "ddaddddrdadddddddaddrddddddadddddrddadddd".toCharArray(),
            "ddddddaddrcdddddaddddddrddcddadddrddddddd".toCharArray(),
            "ddddrddddddaddrdddddadddddrcddddddadddddd".toCharArray(),
            "dddaddrddcddadddddddrcddddddddadrdddddddd".toCharArray(),
            "ddddddadddddrdddddaddrdddcddaddddddrddddd".toCharArray(),
            "dddcdrddddaddrcddddaddddddrddadddddrddddd".toCharArray(),
            "ddddaddrcddddaddddrddaddddddrdddddadddddd".toCharArray(),
            "dddrdddddadddrddaddcddddddaddrddddddrcddd".toCharArray(),
            "ddddddadddddrcdddddaddrdddddadddrdddddddd".toCharArray(),
            "dddrddcdadddddddrdadddddrcdddaddddrdddddd".toCharArray(),
            "ddadddddddrddadddddrddddddaddddddrddddddd".toCharArray(),
            "ddddddadddrdddddaddrcdddddaddrdddddaddddd".toCharArray(),
            "ddddrcdddddaddrdddddadddrcdddddaddddddddd".toCharArray(),
            "ddddadddddddrddddddaddddddrdadddddrdddddd".toCharArray(),
            "ddaddrcdddddadrddddddaddrdddddrcdddaddddd".toCharArray(),
            "dddddddadddrddddadddrddddddadddddrddaddde".toCharArray()
        },

    };

    public static char[][][] boards = normalizeAndFrameBoards(rawBoards);


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
