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
            "aaddcccccccccccccarrraaacccccccccccca".toCharArray(),
            "aarrc           crrraraac          cr".toCharArray(),
            "rrrac           caadrarac          cr".toCharArray(),
            "rrrac                              cr".toCharArray(),
            "araac           ccccccccc          cr".toCharArray(),
            "rrrrc     h     carraarrc          cr".toCharArray(),
            "raaac           carradarc          ca".toCharArray(),
            "arrac           caaaararc          ca".toCharArray(),
            "arrac           crdrraarc          cr".toCharArray(),
            "ararc sx        craarrrac          cr".toCharArray(),
            "adaac           craarrrac          cr".toCharArray(),
            "araacccccccdcccccarrrrarccccccccccccr".toCharArray(),
            "rrrrrarraaaaddrrraraadrarardrraaaaaar".toCharArray(),
            "aaarraradaarraarraaarrararrdaraarrara".toCharArray(),
            "rraraarrrrrrarradararrrraraarraarrara".toCharArray(),
            "rraraarrrrrrarraaaddaarrararraraadrra".toCharArray(),
            "aarrrrrrraraarrdrrrarraaarardrarrraar".toCharArray(),
            "aaarraaraardrrrraraadaaraaraaarrararr".toCharArray()
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
            "sdddddddddddddddcx              ac".toCharArray(),
            "ccccccccccccccddccccccccccccccccac".toCharArray(),
            "c            cddc   p        ddcac".toCharArray(),
            "c            cddc            crcac".toCharArray(),
            "c  cccccccc  cddc            crcac".toCharArray(),
            "c  caaaaaac  cddc            crcac".toCharArray(),
            "c  ca   oac  cddc            crcac".toCharArray(),
            "c  ca    ac  cddc            crcac".toCharArray(),
            "c  ca    ac  cddc            crcac".toCharArray(),
            "c  ca    ac  cddc            crcac".toCharArray(),
            "c  cccddccc  cddc            crcac".toCharArray(),
            "c            cddc     ccc    crcac".toCharArray(),
            "c            cddc            crcac".toCharArray(),
            "ccccccdcccccccddcccccccdccccccrc c".toCharArray(),
            "drddddddddddrrdddddrrd   rrdddrc c".toCharArray(),
            "rrrdddddddrrrddddrdrdr dddddddrd c".toCharArray(),
            "ddrrrdcrcdddrdrdrrddrdcrcrddddcccc".toCharArray(),
            "rdrdddcrcdrddrrrrdddddcrcdddrrddda".toCharArray()


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
