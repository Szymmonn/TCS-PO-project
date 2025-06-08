package io.github.StoneDigger.model.GameObjects.Entities;

import com.badlogic.gdx.math.GridPoint2;

import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.Directions.*;
import io.github.StoneDigger.model.GameObjects.Tiles.EmptyTile;
import io.github.StoneDigger.model.GameObjects.Tiles.RockTile;
import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Interfaces.IDestructable;
import io.github.StoneDigger.model.Interfaces.IMovable;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;
import io.github.StoneDigger.model.Interfaces.IOpponent;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.PlayerManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;

public class Opponent implements IOpponent,IMovable, ISelfUpdate, IDestructable {
    private boolean active = true;
    private GridPoint2 startingPosition;
    private float opponentMoveTime = 0;
    private EDirections moveDirection;
    private final BoardManager boardManager;
    private final UpdateManager updateManager;
    private final PlayerManager playerManager;

    private GridPoint2 pos;

    public Opponent(GridPoint2 start, BoardManager boardManager, UpdateManager updateManager, PlayerManager playerManager) {
        this.updateManager = updateManager;
        this.pos=start;
        this.boardManager = boardManager;
        this.moveDirection = EDirections.RIGHT;
        this.playerManager = playerManager;
    }

    @Override public GridPoint2 getPosition() { return pos; }
    public void setPosition(GridPoint2 p){ pos=p; }

    @Override
    public void setStartingPosition(GridPoint2 startingPosition) {
        this.startingPosition = new GridPoint2(startingPosition);
    }

    @Override
    public void setOnStartingPosition() {
        pos = new GridPoint2(startingPosition);
    }

    public boolean canMove(EDirections dir) {
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
//        System.out.println("next: "+nextTileToOpponent+" left: "+leftTileToOpponent+" right: "+rightTileToOpponent+"\n");
        EDirections[] tmp = {EDirections.UP, EDirections.LEFT, EDirections.DOWN, EDirections.RIGHT};
        int tmpPos = 0;
        for(int i=0;i<4;i++) if(moveDirection == tmp[i]) tmpPos = i;

        if(!(leftTileToOpponent instanceof EmptyTile)) {


            if(nextTileToOpponent instanceof EmptyTile) {
                return moveDirection;
            } else if(rightTileToOpponent instanceof EmptyTile) {
                return tmp[(tmpPos+3)%4];
            } else {
                return tmp[(tmpPos+2)%4];
            }
        } else {
            return tmp[(tmpPos+1)%4];
        }
    }

    @Override public void move(EDirections dir) {
        if(canMove(dir)) pos.add(dir.getDx(), dir.getDy());
    }

    public void destruct() {
        for(int i= pos.x-1;i<=pos.x+1;i++) {
            for(int j = pos.y-1;j<= pos.y+1;j++) {
                ATile tile = boardManager.getTile(new GridPoint2(i,j));

                if(tile instanceof IDestructable) {
                    if (tile instanceof ISelfUpdate) {
                        updateManager.removedFromUpdates((ISelfUpdate) tile);
                    }
                    boardManager.getBoard().setTile(tile.getPosition(), new EmptyTile(tile.getPosition(), boardManager));

                }
            }
        }
        updateManager.removedFromUpdates(this);
        active = false;

    }
    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void update(float delta) {
        /// Killing player
        GridPoint2 playerPos = playerManager.getPosition();

        ///  Opponent Death
        if(boardManager.getTile(new GridPoint2(pos.x,pos.y)) instanceof RockTile) destruct();

        /// DO POPRAWY
        if(playerPos.equals(pos)) playerManager.getPlayer().setOnStartingPosition();

        /// Moving opponent
        opponentMoveTime+=delta;
        if(opponentMoveTime > 0.3f) {
            if (emptyAroundOpponent()) {
                move(EDirections.RIGHT);
                return;
            }
            if(blocksAroundOpponent()) {
                return;
            }
//            System.out.println("CHCE WEJSC W: "+determineMovement()+" A PORUSZAM SIE AKTUALNIE W: "+moveDirection);
            moveDirection = determineMovement();
            move(moveDirection);

            opponentMoveTime = 0;
        }
    }

    public boolean emptyAroundOpponent() {
        if(!(boardManager.getTile(new GridPoint2(pos.x,pos.y+1)) instanceof EmptyTile)) return false;
        if(!(boardManager.getTile(new GridPoint2(pos.x,pos.y-1)) instanceof EmptyTile)) return false;
        if(!(boardManager.getTile(new GridPoint2(pos.x-1,pos.y)) instanceof EmptyTile)) return false;
        if(!(boardManager.getTile(new GridPoint2(pos.x+1,pos.y)) instanceof EmptyTile)) return false;
        if(!(boardManager.getTile(new GridPoint2(pos.x+1,pos.y+1)) instanceof EmptyTile)) return false;
        if(!(boardManager.getTile(new GridPoint2(pos.x+1,pos.y-1)) instanceof EmptyTile)) return false;
        if(!(boardManager.getTile(new GridPoint2(pos.x-1,pos.y+1)) instanceof EmptyTile)) return false;
        if(!(boardManager.getTile(new GridPoint2(pos.x-1,pos.y-1)) instanceof EmptyTile)) return false;

        return true;
    }

    public boolean blocksAroundOpponent() {
        if((boardManager.getTile(new GridPoint2(pos.x,pos.y+1)) instanceof EmptyTile)) return false;
        if((boardManager.getTile(new GridPoint2(pos.x,pos.y-1)) instanceof EmptyTile)) return false;
        if((boardManager.getTile(new GridPoint2(pos.x-1,pos.y)) instanceof EmptyTile)) return false;
        if((boardManager.getTile(new GridPoint2(pos.x+1,pos.y)) instanceof EmptyTile)) return false;
        if((boardManager.getTile(new GridPoint2(pos.x+1,pos.y+1)) instanceof EmptyTile)) return false;
        if((boardManager.getTile(new GridPoint2(pos.x+1,pos.y-1)) instanceof EmptyTile)) return false;
        if((boardManager.getTile(new GridPoint2(pos.x-1,pos.y+1)) instanceof EmptyTile)) return false;
        if((boardManager.getTile(new GridPoint2(pos.x-1,pos.y-1)) instanceof EmptyTile)) return false;

        return true;
    }
}
