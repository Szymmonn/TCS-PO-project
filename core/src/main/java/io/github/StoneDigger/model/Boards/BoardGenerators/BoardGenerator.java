package io.github.StoneDigger.model.Boards.BoardGenerators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.BoardGenerators.BoardValidators.SimpleBoardValidator;

public class BoardGenerator {

    public static char[][] generateBoard(ELevelType levelType , int levelNumber) {
        if(levelType == ELevelType.STANDARD) {
            StandardBoardGenerator standardBoardGenerator = new StandardBoardGenerator();
            return StandardBoardGenerator.generate(levelNumber);

        } else if(levelType == ELevelType.RANDOM) {
            int width = 20 + levelNumber; // Example difficulty scaling
            int height = 15 + levelNumber;
            GridPoint2 startingPos = new GridPoint2(1, 1);
            RandomBoardGenerator randomBoardGenerator = new RandomBoardGenerator(0.1, 0.1, 0.1);
            char[][] board = RandomBoardGenerator.generate(new GridPoint2(width, height), startingPos);

            SimpleBoardValidator boardValidator = new SimpleBoardValidator();

            while(!boardValidator.validate(board)) {
                board = RandomBoardGenerator.generate(new GridPoint2(width, height), startingPos);
            }

            return board;

        } else {
            return null;
        }
    }
}
