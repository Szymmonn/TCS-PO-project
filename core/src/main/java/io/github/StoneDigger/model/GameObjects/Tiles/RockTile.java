package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameObjects.Entities.IEntity;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.*;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;

import java.util.HashMap;
import java.util.Map;


public class RockTile extends ATile implements ISelfUpdate, IWalkableTile {
    private float rockDropTimer=0;
    private boolean moved=false;

    public RockTile(GridPoint2 start) {super(start);}


    @Override
    public boolean isWalkable(EDirections dir) {
        GridPoint2 newPosition = new GridPoint2(this.getPosition().x+dir.getDx(),this.getPosition().y+dir.getDy());
        return LevelManager.getBoard().getTile(newPosition) instanceof EmptyTile;
    }

    @Override
    public void onWalkBy(IEntity entity, EDirections dir) {
        if(entity instanceof Player) {
            move(dir);
        }
    }

    @Override
    public void update(float delta) {
        rockDropTimer += delta;
        if ((moved && rockDropTimer >= 0.3f) || (!moved && rockDropTimer>=0.6f)) {
            processFallingRocks();
            rockDropTimer = 0;
        }
    }

    private void processFallingRocks() {
        Board board = LevelManager.getBoard();
        int x = position.x;
        int y = position.y;

        if (tryFallDown(x, y)) {
            moved = false;
            ((RockTile) board.getTile(new GridPoint2(x, y - 1))).setMoved(true);

        } else if (moved){
            int side = tryRollSideways(x, y);
            if(side!=0) {
                moved = false;
                ((RockTile) board.getTile(new GridPoint2(x + side, y))).setMoved(true);

            }
        } else {
            moved = false;
        }
    }

    private boolean tryFallDown(int x, int y) {
        Board board = LevelManager.getBoard();
        GridPoint2 from = new GridPoint2(x, y);
        GridPoint2 to = new GridPoint2(x, y - 1);

        if (y > 0 && board.getTile(new GridPoint2(x,y-1)) instanceof EmptyTile) {
            moveWithUpdate(from,to);
            return true;
        }
        return false;
    }

    private int tryRollSideways(int x, int y) {
        Board board = LevelManager.getBoard();

        if (!(board.getTile(new GridPoint2(x,y-1)) instanceof EmptyTile)) {
            // Toczenie w prawo
            if (x < board.getWidth() - 1 &&
                board.getTile(new GridPoint2(x+1,y)) instanceof EmptyTile &&
                board.getTile(new GridPoint2(x+1,y-1)) instanceof EmptyTile) {

                GridPoint2 from = new GridPoint2(x, y);
                GridPoint2 to = new GridPoint2(x+1, y);
                moveWithUpdate(from,to);

                return 1;
            }
            // Toczenie w lewo
            else if (x > 0 &&
                board.getTile(new GridPoint2(x-1,y)) instanceof EmptyTile &&
                board.getTile(new GridPoint2(x-1,y-1)) instanceof EmptyTile) {

                GridPoint2 from = new GridPoint2(x, y);
                GridPoint2 to = new GridPoint2(x-1, y);
                moveWithUpdate(from,to);
                return -1;
            }
        }
        return 0;
    }

    public void moveWithUpdate(GridPoint2 from, GridPoint2 to) {
        Board board = LevelManager.getBoard();
        RockTile oldRock = (RockTile) board.getTile(from);
        RockTile newRock = new RockTile(to);

        board.setTile(to, newRock);
        board.setTile(from, new EmptyTile(from));

        UpdateManager.removedFromUpdates(oldRock);
        UpdateManager.addToUpdates(newRock);
    }


    public void move(EDirections dir) {
        Board board = LevelManager.getBoard();
        GridPoint2 oldPosition = new GridPoint2(getPosition().x,getPosition().y);
        GridPoint2 newPosition = new GridPoint2(getPosition().x+dir.getDx(),getPosition().y+dir.getDy());

        moveWithUpdate(oldPosition,newPosition);
    }

    public void setMoved(boolean flag) {
        moved = flag;
    }
}
