package io.github.StoneDigger.model.Boards.BoardGenerators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;

public class BoardGenerator {
    LevelManager levelManager;
    public BoardGenerator(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    public Board generateBoard(int levelNumber) {
        int width = 20 + levelNumber; // Example difficulty scaling
        int height = 15 + levelNumber;
        GridPoint2 startingPos = new GridPoint2(1, 1);


        RandomBoardGenerator generator = new RandomBoardGenerator(
            0.1 + levelNumber * 0.01, // wall density
            0.1 + levelNumber * 0.01, // rock density
            0.05 + levelNumber * 0.005, // diamond density
            levelManager
        );

        return generator.generate(new GridPoint2(width, height), startingPos);
    }
}
