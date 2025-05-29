package io.github.StoneDigger.model.Interfaces;


import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;

public interface IBoardGenerator {
    Board generate(GridPoint2 size, GridPoint2 start);
}
