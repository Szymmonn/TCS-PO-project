package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Classes.Tiles.*;
import io.github.StoneDigger.model.Interfaces.EDirections;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.IMovable;

import java.util.HashMap;

import static io.github.StoneDigger.model.Classes.Board.getTile;

public class Player implements IEntity {
    private GridPoint2 position;

    public Player(GridPoint2 startPosition) {
        position = startPosition;
    }

    public GridPoint2 getPosition() {
        return position;
    }

    public void setPosition(GridPoint2 newPosition) {
        this.position = newPosition;
    }

    @Override
    public boolean tryToMove(EDirections directions) {
        GridPoint2 vector = new GridPoint2();
        switch(directions) {
            case RIGHT: vector.x++;
            case LEFT: vector.x--;
            case UP: vector.y++;
            case DOWN: vector.y--;
        }
        GridPoint2 newPosition = new GridPoint2(position.x+vector.x,position.y+vector.y);
        if(getTile(newPosition) instanceof BrickTile) return false;
        if(getTile(newPosition) instanceof BorderTile) return false;
        if(getTile(newPosition) instanceof RockTile) {
            GridPoint2 newPosition2 = new GridPoint2(newPosition.x+vector.x,newPosition.y+vector.y);
            if(!(getTile(newPosition2) instanceof EmptyTile)) return false;

            Board.setTile(newPosition,new EmptyTile());
            Board.setTile(newPosition2,new RockTile());
        }
        position = newPosition;
        return true;
    }

    @Override
    public boolean canItMoveOnMySpot(IMovable object, EDirections directions) {
        return false;
    }
}
