package io.github.StoneDigger.model.GameObjects.Entities;

import com.badlogic.gdx.math.GridPoint2;

import io.github.StoneDigger.model.Directions.*;
import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.Level.Managers.PlayerManager;

public class Player implements IPlayer {
    private GridPoint2 pos;
    private int score;

    public Player(GridPoint2 start) {
        pos = start;
    }

    @Override public GridPoint2 getPosition() {
        return pos;
    }

    @Override public void setPosition(GridPoint2 p){
        pos = p;
    }

    @Override public boolean canMove(EDirections dir) {
        GridPoint2 np = new GridPoint2(pos.x + dir.getDx(), pos.y + dir.getDy());
        if(np.x < 0 || np.y < 0 || np.x >= LevelManager.getBoard().getWidth() || np.y >= LevelManager.getBoard().getHeight()) return false;
        ATile tile = LevelManager.getBoard().getTile(np);
        return tile.isWalkable(dir);
    }

    @Override public void move(EDirections dir) {
        ATile t = LevelManager.getBoard().getTile(new GridPoint2(pos.x+dir.getDx(),pos.y+dir.getDy()));
        if(!(t.isWalkable(dir))) return;

        /// moving
        pos.add(dir.getDx(), dir.getDy());

        /// Actions

        if(t instanceof DirtTile) {
            ((DirtTile) t).onWalkBy(this,dir);
        } else if (t instanceof EndTile) {
            ((EndTile) t).onWalkBy(this,dir);
        } else if (t instanceof RockTile) {
            ((RockTile) t).onWalkBy(this,dir);
        } else if (t instanceof DiamondTile) {
            ((DiamondTile) t).onWalkBy(this,dir);
        }
    }

    @Override
    public void update(float delta) {
        ATile tilecurrent = LevelManager.getBoard().getTile(pos);
        if(tilecurrent instanceof RockTile) {
            setPosition(new GridPoint2(1, 1));
            int hp = LevelManager.getStats().getHP();
            LevelManager.getStats().setHP(hp-1);
        }

    }

    public int getScore() {
        return score;
    }
}




//package io.github.StoneDigger.model.Classes;
//
//import com.badlogic.gdx.math.GridPoint2;
//import io.github.StoneDigger.model.Classes.Tiles.*;
//import io.github.StoneDigger.model.Interfaces.EDirections;
//import io.github.StoneDigger.model.Classes.Entities.IEntity;
//import io.github.StoneDigger.model.Classes.GameObjects.IMovable;
//
//import static io.github.StoneDigger.model.Classes.Boards.Board.getTile;
//
//public class Player implements IEntity {
//    private GridPoint2 position;
//
//    public Player(GridPoint2 startPosition) {
//        position = startPosition;
//    }
//
//    public GridPoint2 getPosition() {
//        return position;
//    }
//
//    public void setPosition(GridPoint2 newPosition) {
//        this.position = newPosition;
//    }
//
//    @Override
//    public boolean tryToMove(EDirections directions) {
//        GridPoint2 vector = new GridPoint2();
//        switch(directions) {
//            case RIGHT: vector.x++;
//            case LEFT: vector.x--;
//            case UP: vector.y++;
//            case DOWN: vector.y--;
//        }
//        GridPoint2 newPosition = new GridPoint2(position.x+vector.x,position.y+vector.y);
//        if(getTile(newPosition) instanceof BrickTile) return false;
//        if(getTile(newPosition) instanceof BorderTile) return false;
//        if(getTile(newPosition) instanceof RockTile) {
//            GridPoint2 newPosition2 = new GridPoint2(newPosition.x+vector.x,newPosition.y+vector.y);
//            if(!(getTile(newPosition2) instanceof EmptyTile)) return false;
//
//            Board.setTile(newPosition,new EmptyTile());
//            Board.setTile(newPosition2,new RockTile());
//        }
//        position = newPosition;
//        return true;
//    }
//
//    @Override
//    public boolean canItMoveOnMySpot(IMovable object, EDirections directions) {
//        return false;
//    }
//}
