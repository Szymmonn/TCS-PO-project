package io.github.StoneDigger.model.GameObjects.Entities;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Arrays;
import java.util.Random;

import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.Directions.*;
import io.github.StoneDigger.model.GameObjects.Tiles.DiamondTile;
import io.github.StoneDigger.model.GameObjects.Tiles.EmptyTile;
import io.github.StoneDigger.model.GameObjects.Tiles.RockTile;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.LevelManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;

public class Opponent implements IOpponent {
    private float opponentMoveTime = 0;
    private EDirections moveDirection;
    private final BoardManager boardManager;
    private final UpdateManager updateManager;

    private GridPoint2 pos;

    public Opponent(GridPoint2 start, BoardManager boardManager, UpdateManager updateManager) {
        this.updateManager = updateManager;
        this.pos=start;
        this.boardManager = boardManager;
    }

    @Override public GridPoint2 getPosition() { return pos; }
    @Override public void setPosition(GridPoint2 p){ pos=p; }

    @Override public boolean canMove(EDirections dir) {
        /// MAM TĄ FUNKCJE W DUPIE, PRZYKRO MI, JEST TROCHE USELESS
        return true;
    }

    ///  Moving alongside a border

    public EDirections determineMovement() {
        GridPoint2 left = null;
        GridPoint2 next = null;
        GridPoint2 right = null;

        if (moveDirection == EDirections.UP) {
            right = new GridPoint2(pos.x+1,pos.y);
            left = new GridPoint2(pos.x-1,pos.y);
            next = new GridPoint2(pos.x,pos.y+1);
        } else if (moveDirection == EDirections.DOWN) {
            right = new GridPoint2(pos.x-1,pos.y);
            left = new GridPoint2(pos.x+1,pos.y);
            next = new GridPoint2(pos.x,pos.y-1);
        } else if (moveDirection == EDirections.RIGHT) {
            right = new GridPoint2(pos.x,pos.y-1);
            left = new GridPoint2(pos.x,pos.y+1);
            next = new GridPoint2(pos.x+1,pos.y);
        } else if (moveDirection == EDirections.LEFT) {
            right = new GridPoint2(pos.x,pos.y+1);
            left = new GridPoint2(pos.x,pos.y-1);
            next = new GridPoint2(pos.x-1,pos.y);
        }

        ATile leftTileToOpponent = boardManager.getTile(left);
        ATile nextTileToOpponent = boardManager.getTile(next);
        ATile rightTileToOpponent = boardManager.getTile(right);
        EDirections[] tmp = {EDirections.UP, EDirections.LEFT, EDirections.DOWN, EDirections.RIGHT};
        int tmpPos = 0;

        if(!(nextTileToOpponent instanceof EmptyTile)) {

            for(int i=0;i<4;i++) if(moveDirection == tmp[i]) tmpPos = i;

            if(leftTileToOpponent instanceof EmptyTile) {
                return tmp[(tmpPos+1)%4];
            } else if(rightTileToOpponent instanceof EmptyTile) {
                return tmp[(tmpPos+3)%4];
            } else {
                return tmp[(tmpPos+2)%4];
            }
        } else {
            return moveDirection;
        }
    }

    @Override public void move(EDirections dir) {
        if(canMove(dir)) pos.add(dir.getDx(), dir.getDy());
    }

    @Override
    public void update(float delta) {

        opponentMoveTime+=delta;
        if(opponentMoveTime > 0.3f) {
            if (emptyOrBlocksAroundOpponent(false)) {
                move(EDirections.RIGHT);
                return;
            }
            if(emptyOrBlocksAroundOpponent(true)) return;
            move(determineMovement());
        }
    }

    public boolean emptyOrBlocksAroundOpponent(boolean b) {
        if(!(boardManager.getTile(new GridPoint2(pos.x,pos.y+1)) instanceof EmptyTile)) return b;
        if(!(boardManager.getTile(new GridPoint2(pos.x,pos.y-1)) instanceof EmptyTile)) return b;
        if(!(boardManager.getTile(new GridPoint2(pos.x-1,pos.y)) instanceof EmptyTile)) return b;
        if(!(boardManager.getTile(new GridPoint2(pos.x+1,pos.y)) instanceof EmptyTile)) return b;
        if(!(boardManager.getTile(new GridPoint2(pos.x+1,pos.y+1)) instanceof EmptyTile)) return b;
        if(!(boardManager.getTile(new GridPoint2(pos.x+1,pos.y-1)) instanceof EmptyTile)) return b;
        if(!(boardManager.getTile(new GridPoint2(pos.x-1,pos.y+1)) instanceof EmptyTile)) return b;
        if(!(boardManager.getTile(new GridPoint2(pos.x-1,pos.y-1)) instanceof EmptyTile)) return b;

        return true;
    }
}
