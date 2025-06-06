package io.github.StoneDigger.model.GameObjects.Entities;

import com.badlogic.gdx.math.GridPoint2;

import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.IBoard;
import io.github.StoneDigger.model.Directions.*;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.Level.Managers.OpponentManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;

public class Player implements IPlayer {
    private GridPoint2 pos;
    private GridPoint2 startingPosition;
    private final BoardManager boardManager;
    private final ILevelStats levelStats;
    private final UpdateManager updateManager;
//    private final OpponentManager opponentManager;

    public Player(GridPoint2 start, BoardManager boardManager, ILevelStats levelStats, UpdateManager updateManager) {
        pos = start;
        startingPosition = new GridPoint2(start);
        this.boardManager = boardManager;
        this.levelStats = levelStats;
        this.updateManager = updateManager;
//        this.opponentManager = opponentManager;
    }

    public void setOnStartingPosition() {
        pos = new GridPoint2(startingPosition);
    }

    public void setStartingPosition(GridPoint2 startingPosition) {
        this.startingPosition = new GridPoint2(startingPosition);
    }

    @Override public GridPoint2 getPosition() {
        return pos;
    }

    @Override public void setPosition(GridPoint2 p){
        pos = p;
    }

    @Override public boolean canMove(EDirections dir) {
        GridPoint2 np = new GridPoint2(pos.x + dir.getDx(), pos.y + dir.getDy());
        if(np.x < 0 || np.y < 0 || np.x >= boardManager.getWidth() || np.y >= boardManager.getHeight()) return false;
        ATile tile = boardManager.getTile(np);
        return tile.isWalkable(dir);
    }

    @Override public void move(EDirections dir) {
        ATile t = boardManager.getTile(new GridPoint2(pos.x+dir.getDx(),pos.y+dir.getDy()));
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
        ATile currentTile = boardManager.getTile(pos);
        if(currentTile instanceof DiamondTile) {
            levelStats.collectDiamond();
            updateManager.removedFromUpdates((ISelfUpdate) currentTile);
            boardManager.setTile(pos, new EmptyTile(pos, boardManager));
        }
    }

    public void moveOnStart() {
        pos = startingPosition;
    }
}
