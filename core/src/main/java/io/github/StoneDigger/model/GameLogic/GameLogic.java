package io.github.StoneDigger.model.GameLogic;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardValidators.SimpleBoardValidator;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.Level.LevelManagement.ILevelManager;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;

public class GameLogic implements IGameLogic {
    private final LevelManager lvl;
    private boolean gameOver;
    private Board board;
    private Player player;
    private final BoardGenerator gen;
    private final SimpleBoardValidator validator;

    public GameLogic() {
        this.lvl = new LevelManager();
        this.validator = new SimpleBoardValidator();
        this.gen = new BoardGenerator(lvl);
        init();
    }

    @Override
    public void init() {
        Board empty = new Board(new ATile[1+20][1+15], new GridPoint2(1,1));
        lvl.startLevel(1, empty);
        Board full;
        do {
            full = gen.generateBoard(1);
        } while (!validator.validate(full));
        lvl.startLevel(1,board); gameOver = false;
        board = full;
        this.player = new Player(board);
    }

    @Override
    public void tick(float delta) {
        lvl.update(delta);
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }
}
