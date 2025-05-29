package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Classes.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Classes.BoardGenerators.BoardValidators.SimpleBoardValidator;
import io.github.StoneDigger.model.Interfaces.IBoardGenerator;
import io.github.StoneDigger.model.Interfaces.IBoardValidator;
import io.github.StoneDigger.model.Interfaces.IGameLogic;
import io.github.StoneDigger.model.Interfaces.ILevelManager;

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
        do {
            board = gen.generateBoard(1);
        } while (!validator.validate(board));
        this.player = new Player(board);

        lvl.startLevel(1); gameOver = false;
    }

    @Override
    public void tick() {
        lvl.update();
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
