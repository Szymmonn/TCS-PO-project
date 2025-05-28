package io.github.StoneDigger.model.Interfaces;


import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Classes.Board;
import io.github.StoneDigger.model1.models.BoardModel;

public interface IBoardGenerator {
    Board generate(GridPoint2 boardSize, GridPoint2 startingPosition);
}

