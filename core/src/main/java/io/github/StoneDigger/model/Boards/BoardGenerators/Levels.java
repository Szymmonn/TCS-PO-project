package io.github.StoneDigger.model.Boards.BoardGenerators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Level.Managers.LevelManager;

import javax.swing.border.Border;

public class Levels {
    /*
    public static GridPoint2[] startingPoints = {
        new GridPoint2(1, 1),
        new GridPoint2(1, 1),
        new GridPoint2(1, 1),
        new GridPoint2(1, 1),
        new GridPoint2(1, 1)
    };

    private static final char[][][] rawBoards = {
        /// TO DO: MOVE TO CONFIG
        {

        },
        {},

//        {
//            rrrrrrrrrrrrdadadddcd dccaaaar ar cxac
//            rrrrrrrrrrrrrracdradr daaacccaddcdc ac
//            ddddddddddddddddddcccccccccccccccdc ac
//                           rrr a   aa         c ac
//                           r d a    aa      aac ac
//                             r a     dd       c ac
//                              ccccccccddccccccc ac
//                              c       cddc    c ac
//                             rcc        cddc  c ac
//                             rcacdcccdddddddc c ac
//                             rc           cddcc ac
//                          rrccccccccrccccc cddc  c
//                           rrccaa           cdd  c
//              a a a a a      rcccccccccccccccccccc
//                              rrrrrrrrrrrrrrrrrrr .toCharArray()
//        },

//        {
            bbbbbbbbbbbbbbbbbbbbbbcc...a...c...a...ccbbcccccccccccccccccccbbo..................bbo..................bb.........x........obb.........s........obb...................bb.....d.......d.....bbo....r.......r.....bbo..................bb................oo.bbbbbbbbbbbbbbbbbbbbbb
//
//        },

        {
            s   ccccc       ccccc  x
                caaac       caaac
                caaac       caaac
                caaac       caaac
                ccccc       ccccc
                d o d       d o d
                d   d       d   d
                d   d    h  d   d
                d ddd       d ddd
                 d           d
                 r           r      .toCharArray()

        },

//
//        {
//            sdddddddddddddddcx              ac
//            ccccccccccccccddccccccccccccccccac
//            c            cddc   p        ddcac
//            c            cddc            crcac
//            c  cccccccc  cddc            crcac
//            c  caaaaaac  cddc            crcac
//            c  ca   oac  cddc            crcac
//            c  ca    ac  cddc            crcac
//            c  ca    ac  cddc            crcac
//            c  ca    ac  cddc            crcac
//            c  cccddccc  cddc            crcac
//            c            cddc     ccc    crcac
//            c            cddc            crcac
//            ccccccdcccccccddcccccccdccccccrc c
//            drddddddddddrrdddddrrd   rrdddrc c
//            rrrdddddddrrrddddrdrdr dddddddrd c
//            ddrrrdcrcdddrdrdrrddrdcrcrddddcccc
//            rdrdddcrcdrddrrrrdddddcrcdddrrddda.toCharArray()
//
//
//        },

        {
            aaddcccccccccccccarrraaacccccccccccca
            aarrc           crrraraac          cr
            rrrac           caadrarac          cr
            rrrac                              cr
            araac           ccccccccc         pcr
            rrrrc     h     carraarrc          cr
            raaac           carradarc          ca
            arrac           caaaararc          ca
            arrac           crdrraarc          cr
            ararc sx        craarrrac          cr
            adaac           craarrrac          cr
            araacccccccdcccccarrrrarccccccccccccr
            rrrrrarraaaaddrrraraadrarardrraaaaaar
            aaarraradaarraarraaarrararrdaraarrara
            rraraarrrrrrarradararrrraraarraarrara
            rraraarrrrrrarraaaddaarrararraraadrra
            aarrrrrrraraarrdrrrarraaarardrarrraar
            aaarraaraardrrrraraadaaraaraaarrararr.toCharArray()
        },
////
//
//////
//////
////        {
////            sdddrdddcddrddaddddd
////            ddccrrdadddrdrcdcddd
////            dddddddrdcdddddraddd
////            ddrrdcdadrrddddrcddd
////            ddaddrrdddddrcdddddd
////            dddddcdddccddaddrddd
////            ddcdddddraddrddcdddd
////            ddrdddddddcdddddrcdd
////            dddrdddcdrdadddddrdd
////            ddddddddddrdddddadde.toCharArray()
////        },
//
//        {
//            sddddadddrdddcddddddddda
//            ddrcdrdddrdccdrrddaddddd
//            dddddrcdaddddddrdcddrdcd
//            dddddrddcddaddrcddddrddd
//            ddccdadddddrcdddddaddcdd
//            dddddrdddddrdddddrdddddd
//            ddrdddddrcdddcddadrddddd
//            ddadccdrdddddrddcdddrcdd
//            ddddddaddrddcdddddaddddd
//            ddrddddddaddrcdddddrcddd
//            dddddddddrddadddrddddddd
//            dddddddrcddddddrddaddcde.toCharArray()
//        },


        {
            sddddcdddaddrddddddrcddaddddddd
            ddrcdddrddcdaddddrdddddrdcdrddd
            ddaddrddccdddddaddddrcddddddrdd
            dddddddddrddddddccdrddadddddcdd
            dddrddcddaddrdddddrddcddddraddd
            dddddadddddcddrdddddadddddddddd
            ddrddddddddddadrcddddddccddrddd
            dddadcdrcddddddrdddaddddddrddcd
            ddcddddddddaddrcddddrddaddddrdd
            ddddddrdddcddaddddddcdrdddddddd
            ddddrddaddddddddrddcddddadddrdd
            dddcdddddrdddddadddddrddcdddddd
            ddddaddcdrddddddrdddadddddrdddd
            ddddddddddddcdaddddddrcddddddde.toCharArray()
        },
        {
            sddddrdddcdddddddrdddaddddcddddddddd
            ddccdadrdddddadcdddddrdddddrdadddcdd
            ddddddrcdddrddddccdadddddrddddrcdddd
            ddrdddddaddcddrdddddaddrcdddddaddddd
            ddadddrdddddddadddddrddddddcddrddddd
            dddddddddrcdaddddrddddddadrdddcddddd
            ddddrdaddddddrddccddddaddrddddddaddd
            dddddddddrdcddadddrdddddrcddddaddcdd
            ddrcddddddrdddddadddcdrddddadddddddd
            dddddaddcdddddrddddddaddcddddddrdddd
            dddcdrddddddaddrddddrcddddddaddrdddd
            dddaddddrddddddccddrdddddaddddddcddd
            ddddddadrdddddddddaddrcddddrdddddddd
            dddrdcdddadddrcdddddrddddddcddaddddd
            dddddddddrddadddddddrddadddrdddddddd
            ddadddcdddddddaddddddrcddddaddcdddde.toCharArray()
        },

        {
            sddaddrcdddddaddddrddaddcddddadddddrddddd
            ddrcddddrddddddrcdadddddddrddcdadddddrddd
            dddddddadddrcddddaddrddddddaddrddddddaddd
            ddcddrddddaddddddrddcdddddaddrdddcddddadd
            ddddddadddrddaddddrdadddddrcddddaddrddddd
            dddrddcdddddrcddddddadddrdddddaddrcdddddd
            ddaddddrdadddddddaddrddddddadddddrddadddd
            ddddddaddrcdddddaddddddrddcddadddrddddddd
            ddddrddddddaddrdddddadddddrcddddddadddddd
            dddaddrddcddadddddddrcddddddddadrdddddddd
            ddddddadddddrdddddaddrdddcddaddddddrddddd
            dddcdrddddaddrcddddaddddddrddadddddrddddd
            ddddaddrcddddaddddrddaddddddrdddddadddddd
            dddrdddddadddrddaddcddddddaddrddddddrcddd
            ddddddadddddrcdddddaddrdddddadddrdddddddd
            dddrddcdadddddddrdadddddrcdddaddddrdddddd
            ddadddddddrddadddddrddddddaddddddrddddddd
            ddddddadddrdddddaddrcdddddaddrdddddaddddd
            ddddrcdddddaddrdddddadddrcdddddaddddddddd
            ddddadddddddrddddddaddddddrdadddddrdddddd
            ddaddrcdddddadrddddddaddrdddddrcdddaddddd
            dddddddadddrddddadddrddddddadddddrddaddde.toCharArray()
        },

    };

    public static char[][][] boards = normalizeAndFrameBoards(rawBoards);

    public static char[][][] normalizeAndFrameBoards(char[][][] rawBoards) {
        if (rawBoards == null) throw new IllegalArgumentException(Brak plansz.);

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


     */
}
