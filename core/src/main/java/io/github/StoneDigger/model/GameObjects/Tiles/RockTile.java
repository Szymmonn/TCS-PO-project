package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameObjects.Entities.IEntity;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Directions.*;


public class RockTile extends ATile implements ISelfUpdate, IWalkableTile {

    public RockTile(LevelManager levelManager) {
        super(levelManager);
    }
    public RockTile(LevelManager levelManager, GridPoint2 start) {super(levelManager, start);}


    @Override
    public boolean isWalkable(EDirections dir) {
        GridPoint2 newPosition = new GridPoint2(this.getPosition().x+dir.getDx(),this.getPosition().y+dir.getDy());
        return levelManager.getCurrentBoard().getTile(newPosition) instanceof EmptyTile;
    }

    @Override
    public void onWalkBy(IEntity entity, EDirections dir) {
        if(entity instanceof Player) {
            move(dir);
        }
    }

    @Override
    public void update() {

    }

    public void move(EDirections dir) {
        Board board = levelManager.getCurrentBoard();
        GridPoint2 oldPosition = new GridPoint2(getPosition().x,getPosition().y);
        GridPoint2 newPosition = new GridPoint2(getPosition().x+dir.getDx(),getPosition().y+dir.getDy());

        EmptyTile newTile = new EmptyTile(levelManager);
        ATile newRockTile = new RockTile(levelManager);

        board.setTile(oldPosition,newTile);
        board.setTile(newPosition,newRockTile);
    }
}
