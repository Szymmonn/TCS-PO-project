package io.github.StoneDigger.model.Classes.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Classes.Player;
import io.github.StoneDigger.model.Interfaces.*;


public class RockTile extends ATile implements ISelfUpdate, IWalkableTile {

    public RockTile(ILevelManager levelManager) {
        super(levelManager);
    }

    @Override
    public boolean isWalkable(EDirections dir) {
        GridPoint2 newPosition = new GridPoint2(this.getPosition().x+dir.dx,this.getPosition().y+dir.dy);
        return levelManager.getCurrentBoard().getTile(newPosition) instanceof EmptyTile;
    }

    @Override
    public void onWalkBy(IEntity entity,EDirections dir) {
        if(entity instanceof Player) {
            move(dir);
        }
    }

    @Override
    public void update() {

    }

    public void move(EDirections dir) {
        IBoard board = levelManager.getCurrentBoard();
        GridPoint2 oldPosition = new GridPoint2(getPosition().x,getPosition().y);
        GridPoint2 newPosition = new GridPoint2(getPosition().x+dir.dx,getPosition().y+dir.dy);

        EmptyTile newTile = new EmptyTile(levelManager);
        ATile newRockTile = new RockTile(levelManager);

        board.setTile(oldPosition,newTile);
        board.setTile(newPosition,newRockTile);
    }
}
