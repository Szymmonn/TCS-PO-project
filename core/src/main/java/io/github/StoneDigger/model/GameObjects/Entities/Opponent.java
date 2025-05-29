package io.github.StoneDigger.model.GameObjects.Entities;

import com.badlogic.gdx.math.GridPoint2;
import java.util.Random;

import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameObjects.Entities.Hunting.IHunting;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.Directions.*;

public class Opponent implements IOpponent {
    private GridPoint2 pos;
    private final Board board;
    private final Random rnd = new Random();

    public Opponent(Board board, GridPoint2 start){ this.board=board; this.pos=start; }
    @Override public GridPoint2 getPosition() { return pos; }
    @Override public void setPosition(GridPoint2 p){ pos=p; }
    @Override public boolean canMove(EDirections dir) {
        GridPoint2 np = new GridPoint2(pos.x+dir.getDx(), pos.y+dir.getDy());
        if(np.x<0||np.y<0||np.x>=board.getWidth()||np.y>=board.getHeight()) return false;
        ATile tile = board.getTile(np);
        return tile.isWalkable(EDirections.UP);
    }
    @Override public void move(EDirections dir) {
        if(canMove(dir)) pos.add(dir.getDx(), dir.getDy());
    }

    @Override public void kill(IEntity target) {
        // Trigger opponent's death
        if (target instanceof IHunting) {
            ((IHunting) target).onKilled(this);
        }
    }


    @Override
    public void update(float delta) {

    }
}
