package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;
import io.github.StoneDigger.model.Interfaces.IWalkableTile;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;
import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

public class DiamondTile extends ATile implements IWalkableTile, ISelfUpdate {

    protected float dropDiamondTimer = 0f;
    protected int moved = 0;

    protected UpdateManager updateManager;
    private final WhatChanged whatChanged;

    public DiamondTile(GridPoint2 start, BoardManager boardManager, UpdateManager updateManager, WhatChanged whatChanged) {
        this.position = start;
        this.boardManager = boardManager;
        this.updateManager = updateManager;
        this.whatChanged = whatChanged;
    }

    @Override
    public boolean isWalkable(EDirections dir) {
        return true;
    }

    @Override
    public void onWalkBy(IEntity entity, EDirections dir) {
        /// Catching diamonds left to player
    }

    @Override
    public void update(float delta) {
        dropDiamondTimer += delta;

        boolean shouldProcess = (moved > 0 && dropDiamondTimer >= 0.3f) ||
            (moved == 0 && dropDiamondTimer >= 0.6f);

        if (shouldProcess) {
            processFallingDiamonds();
            dropDiamondTimer = 0f;
        }
    }

    protected void processFallingDiamonds() {
        int x = position.x;
        int y = position.y;

        if (tryFallDown(x, y)) {
            ((DiamondTile) boardManager.getTile(new GridPoint2(x, y - 1))).setMoved(moved + 1);
            moved = 0;

        } else if (moved > 0) {
            whatChanged.diamondFell();
            int side = tryRollSideways(x, y);
            if (side != 0) {
                ((DiamondTile) boardManager.getTile(new GridPoint2(x + side, y))).setMoved(moved + 1);
            }
            moved = 0;
        }
    }

    protected boolean tryFallDown(int x, int y) {
        if (y <= 0) return false;

        GridPoint2 to = new GridPoint2(x, y - 1);
        if (boardManager.getTile(to) instanceof EmptyTile) {
            moveWithUpdate(position, to);
            return true;
        }
        return false;
    }

    public boolean tryFallRight(int x, int y) {
        return x < boardManager.getWidth() - 1 &&
            boardManager.getTile(new GridPoint2(x + 1, y)) instanceof EmptyTile &&
            boardManager.getTile(new GridPoint2(x + 1, y - 1)) instanceof EmptyTile;
    }

    public boolean tryFallLeft(int x, int y) {
        return x > 0 &&
            boardManager.getTile(new GridPoint2(x - 1, y)) instanceof EmptyTile &&
            boardManager.getTile(new GridPoint2(x - 1, y - 1)) instanceof EmptyTile;
    }

    protected int tryRollSideways(int x, int y) {
        if (!(boardManager.getTile(new GridPoint2(x, y - 1)) instanceof EmptyTile)) {
            /// Roll right
            if (tryFallRight(x, y)) {
                moveWithUpdate(position, new GridPoint2(x + 1, y));
                return 1;
            }

            /// Roll left
            if (tryFallLeft(x, y)) {
                moveWithUpdate(position, new GridPoint2(x - 1, y));
                return -1;
            }
        }

        return 0;
    }

    public void moveWithUpdate(GridPoint2 from, GridPoint2 to) {
        DiamondTile oldDiamond = (DiamondTile) boardManager.getTile(from);
        DiamondTile newDiamond = new DiamondTile(to, boardManager, updateManager, whatChanged);

        boardManager.setTile(to, newDiamond);
        boardManager.setTile(from, new EmptyTile(from, boardManager));

        updateManager.removedFromUpdates(oldDiamond);
        updateManager.addToUpdates(newDiamond);
    }

    public void setMoved(int flag) {
        this.moved = flag;
    }
}
