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

public class LevelManager implements ILevelManager {
    private Board board;
    private final List<IEntity> entities = new ArrayList<>();
    private final List<ATile> tiles = new ArrayList<>();
    private final LevelStats stats = new LevelStats();

    @Override
    public void resetLevel() {
        startLevel(1,board);
    }

    @Override
    public void startLevel(int index,Board board) {
        this.board = board;
        entities.clear();
        entities.add(new Player(board));
        tiles.clear();
        for (int i = 0; i < board.getWidth(); i++)
            for(int j = 0; j < board.getHeight(); j++)
                tiles.add(board.getTile(new GridPoint2(i,j)));
        //entities.add(new Opponent(board, new GridPoint2(5,5)));
    }

    @Override
    public Board getCurrentBoard() {
        return board;
    }

    @Override
    public ILevelStats getStats() {
        return stats;
    }

    @Override
    public void update(float delta) {
        for(IEntity e : entities) if(e instanceof ISelfUpdate) ((ISelfUpdate) e).update(delta);
        for(ATile e : tiles) if(e instanceof ISelfUpdate) ((ISelfUpdate) e).update(delta);
    }

}
