package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameObjects.Entities.IEntity;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.*;
import io.github.StoneDigger.model.Level.Managers.PlayerManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;

public class DiamondTile extends ATile implements IWalkableTile, ISelfUpdate {
    private float dropDiamondTimer = 0;
    private int moved = 0;
    private UpdateManager updateManager;

    public DiamondTile(GridPoint2 start, BoardManager boardManager, UpdateManager updateManager) {
        this.boardManager = boardManager;
        this.position = start;
        this.updateManager = updateManager;
    }

    @Override public boolean isWalkable(EDirections dir) {
        return true;
    }

    @Override
    public void onWalkBy(IEntity entity, EDirections dir) {
    }

    @Override
    public void update(float delta) {
        dropDiamondTimer += delta;
        if ((moved>0 && dropDiamondTimer >= 0.3f) || (moved==0 && dropDiamondTimer >=0.6f)) {
            processFallingDiamonds();
            dropDiamondTimer = 0;
        }
    }

    private void processFallingDiamonds() {
        int x = position.x;
        int y = position.y;

        if (tryFallDown(x, y)) {
            ((DiamondTile) boardManager.getTile(new GridPoint2(x, y - 1))).setMoved(moved+1);
            moved = 0;

        } else if (moved>0){
            int side = tryRollSideways(x, y);
            if(side!=0) {
                ((DiamondTile) boardManager.getTile(new GridPoint2(x + side, y))).setMoved(moved+1);
                moved = 0;
            } else {
                moved = 0;
            }
        }
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

        if (!(boardManager.getTile(new GridPoint2(x,y-1)) instanceof EmptyTile)) {
            // Toczenie w prawo
            if (tryFallRight(x,y)) {

                GridPoint2 from = new GridPoint2(x, y);
                GridPoint2 to = new GridPoint2(x+1, y);
                moveWithUpdate(from,to);

                return 1;
            }
            // Toczenie w lewo
            else if (tryFallLeft(x,y)) {

                GridPoint2 from = new GridPoint2(x, y);
                GridPoint2 to = new GridPoint2(x-1, y);
                moveWithUpdate(from,to);

                return -1;
            }
        }
        return 0;
    }

    public void moveWithUpdate(GridPoint2 from, GridPoint2 to) {
        DiamondTile oldDiamond = (DiamondTile) boardManager.getTile(from);
        DiamondTile newDiamond = new DiamondTile(to, boardManager, updateManager);

        boardManager.setTile(to, newDiamond);
        boardManager.setTile(from, new EmptyTile(from, boardManager));

        updateManager.removedFromUpdates(oldDiamond);
        updateManager.addToUpdates(newDiamond);
    }

    public void setMoved(int flag) {
        moved = flag;
    }
}
