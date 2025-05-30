package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameObjects.Entities.IEntity;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.*;
import io.github.StoneDigger.model.Level.Managers.PlayerManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;

public class DiamondTile extends ATile implements IWalkableTile, ISelfUpdate {
    float rockDiamondTimer=0;
    int moved=0;

    public DiamondTile(GridPoint2 start) {super(start);}


    @Override public boolean isWalkable(EDirections dir) {
        return true;
    }

    @Override
    public void onWalkBy(IEntity entity, EDirections dir) {
        if(entity instanceof Player) {
            LevelManager.getStats().collectDiamond();
            this.destroy();
        }
    }

    @Override
    public void update(float delta) {
        rockDiamondTimer += delta;
        if ((moved>0 && rockDiamondTimer >= 0.3f) || (moved==0 && rockDiamondTimer>=0.6f)) {
            //processFallingDiamonds();
            rockDiamondTimer = 0;
        }
    }

    private void processFallingDiamonds() {
        Board board = LevelManager.getBoard();
        int x = position.x;
        int y = position.y;

        if (tryFallDown(x, y)) {
            ((RockTile) board.getTile(new GridPoint2(x, y - 1))).setMoved(moved+1);
            moved = 0;

        } else if (moved>0){
            int side = tryRollSideways(x, y);
            if(side!=0) {
                ((RockTile) board.getTile(new GridPoint2(x + side, y))).setMoved(moved+1);
                moved = 0;
            } else {
                moved = 0;
            }
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

    public boolean tryFallRight(int x,int y) {
        Board board = LevelManager.getBoard();

        return x < board.getWidth() - 1 &&
            board.getTile(new GridPoint2(x+1,y)) instanceof EmptyTile &&
            board.getTile(new GridPoint2(x+1,y-1)) instanceof EmptyTile;
    }

    public boolean tryFallLeft(int x,int y) {
        Board board = LevelManager.getBoard();

        return x > 0 &&
            board.getTile(new GridPoint2(x-1,y)) instanceof EmptyTile &&
            board.getTile(new GridPoint2(x-1,y-1)) instanceof EmptyTile;
    }

    private int tryRollSideways(int x, int y) {
        Board board = LevelManager.getBoard();
        GridPoint2 pos = PlayerManager.getPlayer().getPosition();

        if (!(board.getTile(new GridPoint2(x,y-1)) instanceof EmptyTile)) {
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
        Board board = LevelManager.getBoard();
        RockTile oldRock = (RockTile) board.getTile(from);
        RockTile newRock = new RockTile(to);

        board.setTile(to, newRock);
        board.setTile(from, new EmptyTile(from));

        UpdateManager.removedFromUpdates(oldRock);
        UpdateManager.addToUpdates(newRock);
    }
}
