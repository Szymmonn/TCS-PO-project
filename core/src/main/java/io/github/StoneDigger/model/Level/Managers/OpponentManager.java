package io.github.StoneDigger.model.Level.Managers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.OpponentAI;

public class OpponentManager {
    private final OpponentAI opponent;

    OpponentManager(GridPoint2 start, BoardManager boardManager, UpdateManager updateManager,PlayerManager playerManager) {
        opponent = new OpponentAI(start, boardManager, updateManager,playerManager);
    }

    public OpponentAI getOpponent() {
        return opponent;
    }

    public void moveOpponent(EDirections direction) {
        opponent.move(direction);
    }
}
