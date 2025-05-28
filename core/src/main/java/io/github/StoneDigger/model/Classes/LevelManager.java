package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Classes.BoardGenerators.BoardValidators.SimpleBoardValidator;
import io.github.StoneDigger.model.Interfaces.ILevelManager;

import java.util.Random;

public class LevelManager implements ILevelManager {
    Board board;

    @Override
    public void resetLevel() {
        setNewLevel(0);
    }

    @Override
    public void setNewLevel(int levelNumber) {
        RandomBoardGenerator randomBoardGenerator = new RandomBoardGenerator(0.1, 0.1, 0.1, new Random());
        SimpleBoardValidator simpleBoardValidator = new SimpleBoardValidator();
        board = randomBoardGenerator.generate(new GridPoint2(2, 2), new GridPoint2(30, 40));

        while(!simpleBoardValidator.validate(board)) {
            board = randomBoardGenerator.generate(new GridPoint2(2, 2), new GridPoint2(30, 40));
        }
    }
}
