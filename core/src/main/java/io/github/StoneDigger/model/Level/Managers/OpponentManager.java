package io.github.StoneDigger.model.Level.Managers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.IBoard;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.Opponent;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Level.ILevelStats;

public class OpponentManager {
    private Opponent opponent = null;

    OpponentManager(GridPoint2 start, BoardManager boardManager, UpdateManager updateManager) {
        opponent = new Opponent(start, boardManager, updateManager);
    }

    public Opponent getOpponent() {
        return opponent;
    }

    public void moveOpponent(EDirections direction) {
        opponent.move(direction);
    }
}
