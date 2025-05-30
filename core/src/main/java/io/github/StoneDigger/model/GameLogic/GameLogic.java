package io.github.StoneDigger.model.GameLogic;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardValidators.SimpleBoardValidator;
import io.github.StoneDigger.model.Boards.BoardGenerators.ELevelType;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;
import io.github.StoneDigger.model.Level.Managers.PlayerManager;

import static io.github.StoneDigger.model.Level.Managers.LevelManager.setBoard;



/*
responsible for game cycle
 */
public class GameLogic {

    public static void startTheGame(ELevelType levelType) {
        /// Level Manager
        LevelManager.setGameOver(false);
        ///  Board setup
        //TODO: wywalic to
        Board empty = new Board(new ATile[1 + 20][1 + 15], new GridPoint2(1, 1));
        setBoard(empty);

        /// Player manager
        PlayerManager.setPlayer(new Player(new GridPoint2(1, 1)));
//        LevelManager.startMechanics(1, empty);
//        Board full;
//        full = BoardGenerator.generateBoard(ELevelType.STANDARD, 1);
//        do {
//            full = BoardGenerator.generateBoard(ELevelType.RANDOM, 1);
//        } while (!validator.validate(full));
//        LevelManager.startLevel(1, full);
//        LevelManager.setBoard(full);
        LevelManager.startNewLevel();
//        /// Update manager
//        UpdateManager.addToUpdates(PlayerManager.getPlayer());
//        for (int i = 0; i < LevelManager.getBoard().getWidth(); i++) {
//            for (int j = 0; j < LevelManager.getBoard().getHeight(); j++) {
//                ATile tile = LevelManager.getBoard().getTile(new GridPoint2(i, j));
//                if (tile instanceof ISelfUpdate)
//                    UpdateManager.addToUpdates((ISelfUpdate) tile);
//            }
//        }
//        LevelManager.setLevelNumber(0);
    }

    public static void tick(float delta) {
        UpdateManager.updateAll(delta);
    }


}
