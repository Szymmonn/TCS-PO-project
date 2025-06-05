package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameObjects.Entities.IEntity;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.*;
import io.github.StoneDigger.model.Level.Managers.PlayerManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;

public class RockTile extends ATile implements ISelfUpdate, IWalkableTile {
    private float rockDropTimer = 0;
    private int moved = 0;
    private UpdateManager updateManager;
    private PlayerManager playerManager;

    public RockTile(GridPoint2 start, BoardManager boardManager, UpdateManager updateManager, PlayerManager playerManager) {
        this.boardManager = boardManager;
        this.position = start;
        this.updateManager = updateManager;
        this.playerManager = playerManager;
    }

    @Override
    public boolean isWalkable(EDirections dir) {
        if(dir == EDirections.UP) return false;
        if(moved>0) return false;
        GridPoint2 newPosition = new GridPoint2(this.getPosition().x+dir.getDx(),this.getPosition().y+dir.getDy());
        return boardManager.getBoard().getTile(newPosition) instanceof EmptyTile;
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
        if ((moved>0 && rockDropTimer >= 0.3f) || (moved==0 && rockDropTimer>=0.6f)) {
            processFallingRocks();
            rockDropTimer = 0;
        }
    }

    private void processFallingRocks() {
        int x = position.x;
        int y = position.y;

        if(playerStops(x,y)) {
            rockDropTimer = 0;
            return;
        }

        if (tryFallDown(x, y)) {
            ((RockTile) boardManager.getTile(new GridPoint2(x, y - 1))).setMoved(moved+1);
            moved = 0;

        } else if (moved>0){
            int side = tryRollSideways(x, y);
            if(side!=0) {
                ((RockTile) boardManager.getTile(new GridPoint2(x + side, y))).setMoved(moved+1);
                moved = 0;
            } else {
                moved = 0;
            }
        }
    }

    private boolean playerStops(int x,int y) {
        GridPoint2 pos = playerManager.getPlayer().getPosition();

        if(pos.x == x && pos.y + 1 == y && moved < 1) return true;
        if(!(boardManager.getTile(new GridPoint2(x,y-1)) instanceof EmptyTile)) {

            if (tryFallRight(x, y) && pos.x == x + 1 && pos.y == y) {
                if(tryFallLeft(x,y)) return false;
                else {
                    moved=0;
                    return true;
                }
            }
            if (tryFallLeft(x, y) && pos.x == x - 1 && pos.y == y) {
                if(tryFallRight(x, y)) return false;
                else {
                    moved = 0;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean tryFallDown(int x, int y) {
        GridPoint2 from = new GridPoint2(x, y);
        GridPoint2 to = new GridPoint2(x, y - 1);

        if (y > 0 && boardManager.getTile(new GridPoint2(x,y-1)) instanceof EmptyTile) {
            moveWithUpdate(from,to);
            return true;
        }
        return false;
    }

    public boolean tryFallRight(int x,int y) {

        return x < boardManager.getWidth() - 1 &&
            boardManager.getTile(new GridPoint2(x+1,y)) instanceof EmptyTile &&
            boardManager.getTile(new GridPoint2(x+1,y-1)) instanceof EmptyTile;
    }

    public boolean tryFallLeft(int x,int y) {

        return x > 0 &&
            boardManager.getTile(new GridPoint2(x-1,y)) instanceof EmptyTile &&
            boardManager.getTile(new GridPoint2(x-1,y-1)) instanceof EmptyTile;
    }

    private int tryRollSideways(int x, int y) {
        GridPoint2 pos = playerManager.getPlayer().getPosition();

        if (!(boardManager.getTile(new GridPoint2(x,y-1)) instanceof EmptyTile)) {
            // Toczenie w prawo
            if (tryFallRight(x,y) && !(pos.x == x+1 && pos.y == y)) {

                GridPoint2 from = new GridPoint2(x, y);
                GridPoint2 to = new GridPoint2(x+1, y);
                moveWithUpdate(from,to);

                return 1;
            }
            // Toczenie w lewo
            else if (tryFallLeft(x,y) && !(pos.x == x-1 && pos.y == y)) {

                GridPoint2 from = new GridPoint2(x, y);
                GridPoint2 to = new GridPoint2(x-1, y);
                moveWithUpdate(from,to);

                return -1;
            }
        }
        return 0;
    }

    public void moveWithUpdate(GridPoint2 from, GridPoint2 to) {
        RockTile oldRock = (RockTile) boardManager.getTile(from);
        RockTile newRock = new RockTile(to, boardManager, updateManager, playerManager);

        boardManager.setTile(to, newRock);
        boardManager.setTile(from, new EmptyTile(from, boardManager));

        updateManager.removedFromUpdates(oldRock);
        updateManager.addToUpdates(newRock);
    }


    public void move(EDirections dir) {
        GridPoint2 oldPosition = new GridPoint2(getPosition().x,getPosition().y);
        GridPoint2 newPosition = new GridPoint2(getPosition().x+dir.getDx(),getPosition().y+dir.getDy());

        moveWithUpdate(oldPosition,newPosition);
    }

    public void setMoved(int flag) {
        moved = flag;
    }
}
