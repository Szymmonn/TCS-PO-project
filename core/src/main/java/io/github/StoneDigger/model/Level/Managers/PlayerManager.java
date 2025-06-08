package io.github.StoneDigger.model.Level.Managers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.IBoard;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.OpponentAI;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Interfaces.IPlayer;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.TileChangers.PlayerChanger;
import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

public class PlayerManager {
    private Player player = null;

    PlayerManager(GridPoint2 start, BoardManager boardManager, ILevelStats levelStats, UpdateManager updateManager, WhatChanged whatChanged) {
        player = new PlayerChanger(start, boardManager, levelStats, updateManager, whatChanged);
    }

    public Player getPlayer() {
        return player;
    }

    public void movePlayer(EDirections direction) {
        player.move(direction);
    }

    public GridPoint2 getPosition() {return player.getPosition();}

    public void movePlayerToStart() {
        player.moveOnStart();
    }
}
