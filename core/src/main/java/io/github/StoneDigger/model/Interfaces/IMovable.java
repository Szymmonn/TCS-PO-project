package io.github.StoneDigger.model.Interfaces;

import com.badlogic.gdx.math.GridPoint2;

public interface IMovable {
    GridPoint2 getPosition();
    void setPosition(GridPoint2 newPosition);
    boolean tryToMove(EDirections directions);

    default void move(EDirections direction) {
        int x = getPosition().x;
        int y = getPosition().y;

        switch(direction) {
            case RIGHT: setPosition(new GridPoint2(++x,y)); break;
            case LEFT: setPosition(new GridPoint2(--x,y)); break;
            case UP: setPosition(new GridPoint2(x,++y)); break;
            case DOWN: setPosition(new GridPoint2(x,--y)); break;
        }
    }
}
