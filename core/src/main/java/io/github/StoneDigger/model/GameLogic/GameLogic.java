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
        Board empty = new Board(new ATile[1][1], new GridPoint2(1, 1));
        setBoard(empty);

        /// Player manager
        PlayerManager.setPlayer(new Player(new GridPoint2(1, 1)));

        /// Level starting
        LevelManager.startNewLevel();
    }

    public static void tick(float delta) {
        UpdateManager.updateAll(delta);
    }


}
