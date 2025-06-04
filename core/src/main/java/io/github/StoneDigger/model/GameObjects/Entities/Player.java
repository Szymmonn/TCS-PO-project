package io.github.StoneDigger.model.GameObjects.Entities;

import com.badlogic.gdx.math.GridPoint2;

import io.github.StoneDigger.model.Directions.*;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;

public class Player implements IPlayer {
    private GridPoint2 pos;
    private final GridPoint2 startingPosition;
    private int score;

    public Player(GridPoint2 start) {
        pos = start;
        startingPosition = new GridPoint2(start);
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
            setPosition(startingPosition);
            LevelManager.getStats().decreaseHP();
        } else if(tilecurrent instanceof DiamondTile) {
            LevelManager.getStats().collectDiamond();
            UpdateManager.removedFromUpdates((ISelfUpdate) tilecurrent);
            LevelManager.getBoard().setTile(pos, new EmptyTile(pos));
        }

    }

    public int getScore() {
        return score;
    }
}
