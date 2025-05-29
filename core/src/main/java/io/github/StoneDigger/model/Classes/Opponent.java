package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import java.util.Random;

import io.github.StoneDigger.model.Classes.Tiles.ATile;
import io.github.StoneDigger.model.Interfaces.*;

public class Opponent implements IOpponent {
    private GridPoint2 pos;
    private final IBoard board;
    private final Random rnd = new Random();

    public Opponent(IBoard board, GridPoint2 start){ this.board=board; this.pos=start; }
    @Override public GridPoint2 getPosition() { return pos; }
    @Override public void setPosition(GridPoint2 p){ pos=p; }
    @Override public boolean canMove(EDirections dir) {
        GridPoint2 np = new GridPoint2(pos.x+dir.dx, pos.y+dir.dy);
        if(np.x<0||np.y<0||np.x>=board.getWidth()||np.y>=board.getHeight()) return false;
        ATile tile = board.getTile(np);
        return tile.isWalkable(EDirections.UP);
    }
    @Override public void move(EDirections dir) { if(canMove(dir)) pos.add(dir.dx, dir.dy); }
    @Override public void kill(IEntity target) {
        // Trigger opponent's death
        if (target instanceof IHunting) {
            ((IHunting) target).onKilled(this);
        }
    }
    @Override public void onKilled(IEntity killer) {
        // When killed, grant score to the killer if it's a player
        if (killer instanceof IPlayer) {
            ((IPlayer) killer).collect();
        }
        // Additional removal logic can be added here
    }

    @Override
    public void update() {

    }
}
