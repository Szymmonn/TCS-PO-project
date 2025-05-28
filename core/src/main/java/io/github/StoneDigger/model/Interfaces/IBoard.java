package io.github.StoneDigger.model.Interfaces;
import com.badlogic.gdx.math.GridPoint2;

public interface IBoard {
    public GridPoint2 getStartingPoint();
    public GridPoint2 getSize();
    public ITile getTile();
}
