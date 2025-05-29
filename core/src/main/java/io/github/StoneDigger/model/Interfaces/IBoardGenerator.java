package io.github.StoneDigger.model.Interfaces;


import com.badlogic.gdx.math.GridPoint2;

public interface IBoardGenerator {
    IBoard generate(GridPoint2 size, GridPoint2 start);
}
