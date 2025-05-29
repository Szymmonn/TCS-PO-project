package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import java.util.List;
import java.util.ArrayList;

import io.github.StoneDigger.model.Classes.Tiles.ATile;
import io.github.StoneDigger.model.Interfaces.*;

public class LevelManager implements ILevelManager {
    private IBoard board;
    private final List<IEntity> entities = new ArrayList<>();
    private final List<ATile> tiles = new ArrayList<>();
    private final IBoardGenerator gen;
    private final IBoardValidator validator;
    private final ILevelStats stats = new LevelStats();

    public LevelManager(IBoardGenerator gen, IBoardValidator val) {
        this.gen = gen;
        this.validator = val;
    }

    @Override public void resetLevel() {
        startLevel(1);
    };

    @Override public void startLevel(int index) {
        do {
            board = gen.generate(new GridPoint2(20, 15), new GridPoint2(1, 1));
        } while (!validator.validate(board));

        entities.clear();
        entities.add(new Player(board));
        tiles.clear();
        for (int i = 0; i < board.getWidth(); i++)
            for(int j = 0; j < board.getHeight(); j++)
                tiles.add(board.getTile(new GridPoint2(i,j)));
        //entities.add(new Opponent(board, new GridPoint2(5,5)));
    }

    @Override public IBoard getCurrentBoard() {
        return board;
    }

    @Override public ILevelStats getStats() {
        return stats;
    }

    @Override public void update() {
        for(IEntity e : entities) if(e instanceof ISelfUpdate) ((ISelfUpdate) e).update();
        for(IEntity e : entities) if(e instanceof ISelfUpdate) ((ISelfUpdate) e).update();
    }
}
