package io.github.StoneDigger.model.Level.LevelManagement;

import com.badlogic.gdx.math.GridPoint2;
import java.util.List;
import java.util.ArrayList;

import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardValidators.SimpleBoardValidator;
import io.github.StoneDigger.model.GameObjects.Entities.IEntity;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;

public class LevelManager {
    private static Board board;
    private static final List<IEntity> entities = new ArrayList<>();
    private static final List<ATile> tiles = new ArrayList<>();
    private static final LevelStats stats = new LevelStats();

    public static void resetLevel() {
        startLevel(1,board);
    }

    public static void startLevel(int index,Board bboard) {
        board = bboard;
        entities.clear();
        entities.add(new Player(board));
        tiles.clear();
        for (int i = 0; i < board.getWidth(); i++)
            for(int j = 0; j < board.getHeight(); j++)
                tiles.add(board.getTile(new GridPoint2(i,j)));
        //entities.add(new Opponent(board, new GridPoint2(5,5)));
    }

    public static Board getCurrentBoard() {
        return board;
    }

    public static ILevelStats getStats() {
        return stats;
    }

    public static void update(float delta) {
        for(IEntity e : entities) if(e instanceof ISelfUpdate) ((ISelfUpdate) e).update(delta);
        for(ATile e : tiles) if(e instanceof ISelfUpdate) ((ISelfUpdate) e).update(delta);
    }

}
