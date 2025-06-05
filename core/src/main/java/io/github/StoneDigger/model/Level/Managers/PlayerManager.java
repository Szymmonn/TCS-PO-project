package io.github.StoneDigger.model.Level.Managers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.IBoard;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Level.ILevelStats;

public class PlayerManager {
    private final Player player;

    PlayerManager(GridPoint2 start, BoardManager boardManager, ILevelStats levelStats, UpdateManager updateManager) {
        player = new Player(start, boardManager, levelStats, updateManager);
    }

    public Player getPlayer() {
        return player;
    }

    public void movePlayer(EDirections direction) {
        player.move(direction);
    }
}
