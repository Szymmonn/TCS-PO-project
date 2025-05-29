package io.github.StoneDigger.model.GameLogic;

import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardGenerator;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardValidators.SimpleBoardValidator;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
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
