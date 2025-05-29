package io.github.StoneDigger.model.Classes.BoardGenerators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Classes.LevelManager;
import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.model.Interfaces.ILevelManager;

import java.util.Random;

public class BoardGenerator {
     LevelManager levelManager;
    public BoardGenerator(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    public IBoard generateBoard(int levelNumber) {
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
